package com.eduardocaio.movie_library_backend.services;

import java.util.List;
import java.util.UUID;
import java.time.Instant;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.eduardocaio.movie_library_backend.components.EmailServiceImpl;
import com.eduardocaio.movie_library_backend.dto.MovieDTO;
import com.eduardocaio.movie_library_backend.dto.UserDTO;
import com.eduardocaio.movie_library_backend.dto.UserSignupDTO;
import com.eduardocaio.movie_library_backend.entities.UserEntity;
import com.eduardocaio.movie_library_backend.entities.VerificationUserEntity;
import com.eduardocaio.movie_library_backend.enums.StatusUser;
import com.eduardocaio.movie_library_backend.exceptions.SignupException;
import com.eduardocaio.movie_library_backend.repositories.UserRepository;
import com.eduardocaio.movie_library_backend.repositories.VerificationUserRepository;

@Service
public class UserService {

    @Value("${host.front.mail}")
    private String linkFront;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MovieApiService movieApiService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private RoleService roleService;

    @Autowired
    private VerificationUserRepository verificationRepository;

    @Autowired
    private EmailServiceImpl emailService;

    public List<UserDTO> findAll(){
        List<UserEntity> users = userRepository.findAll();
        return users.stream().map(UserDTO::new).toList();
    }

    public UserDTO findById(Long id){
        UserDTO user = new UserDTO(userRepository.findById(id).get());
        return user;
    }


    public void signup(UserSignupDTO newUser){

        if(userRepository.findByUsername(newUser.username()).isPresent()){
            throw new SignupException("Nome de usuário já cadastrado!");
        }
        if(userRepository.findByEmail(newUser.email()).isPresent()){
            throw new SignupException("E-mail já cadastrado!");
        }

        UserEntity user = new UserEntity(newUser);

        user.setPassword(passwordEncoder.encode(newUser.password()));
        user.addRole(roleService.findByDescription("BASIC"));
        user.setStatus(StatusUser.PENDENTE);

        UserEntity userSave = userRepository.save(user);

        VerificationUserEntity verification = new VerificationUserEntity();
        
        verification.setExpiration(Instant.now().plusSeconds(600L));
        verification.setUser(userSave);
        verificationRepository.save(verification);

        emailService.sendSimpleMessage(user.getEmail(), "Confirmação de E-mail - CaJuFlix", "Olá, " + user.getName() + ". É um prazer ter você conosco. Clique no link para confirmar seu e-mail: "+ linkFront + verification.getId());
    }
    
    public void verify(UUID code){
        VerificationUserEntity verification = verificationRepository.findById(code).get();
        if(verification.getId() == code){
            verification.getUser().setStatus(StatusUser.ATIVO);
            userRepository.save(verification.getUser());
            verificationRepository.delete(verification);
        }
    }

    public UserDTO update(UserDTO user){
        UserEntity userEntity = userRepository.findById(user.getId()).get();
        updateData(user, userEntity);
        UserDTO userDTO = new UserDTO(userRepository.save(userEntity));
        return userDTO;
    }

    public void delete(Long id){
        UserEntity user = userRepository.findById(id).get();
        userRepository.delete(user);
    }

    public UserEntity findByUsername(String username){
        UserEntity user = userRepository.findByUsername(username).get();
        return user;
    }

    public void addFavoriteMovie(Long idMovie, String username){
        UserEntity user = userRepository.findByUsername(username).get();
        user.addFavoriteMovie(idMovie);
        userRepository.save(user);
    }

    public void removeFavoriteMovie(Long idMovie, String username){
        UserEntity user = userRepository.findByUsername(username).get();
        user.removeFavoriteMovie(idMovie);
        userRepository.save(user);
    }

    public List<MovieDTO> userFavoriteMovies(String username){
        UserEntity user = userRepository.findByUsername(username).get();
        List<MovieDTO> movies = new ArrayList<>();
        for (Long movie : user.getFavoriteMovies()) {
            movies.add(movieApiService.findMovie(movie));
        }
        return movies;
    }

    private void updateData(UserDTO userDTO, UserEntity userEntity){
        if(userDTO.getName() != null){
            userEntity.setName(userDTO.getName());
        }
        if(userDTO.getUsername() != null){
            userEntity.setUsername(userDTO.getUsername());
        }
    }

}
