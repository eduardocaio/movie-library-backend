package com.eduardocaio.movie_library_backend.services;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eduardocaio.movie_library_backend.dto.MovieDTO;
import com.eduardocaio.movie_library_backend.dto.UserDTO;
import com.eduardocaio.movie_library_backend.dto.UserSignupDTO;
import com.eduardocaio.movie_library_backend.entities.UserEntity;
import com.eduardocaio.movie_library_backend.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MovieApiService movieApiService;

    public List<UserDTO> findAll(){
        List<UserEntity> users = userRepository.findAll();
        return users.stream().map(UserDTO::new).toList();
    }

    public UserDTO findById(Long id){
        UserDTO user = new UserDTO(userRepository.findById(id).get());
        return user;
    }

    public void signup(UserSignupDTO newUser){
        UserEntity user = new UserEntity(newUser);
        userRepository.save(user);
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

    public void addFavoriteMovie(Long idMovie, Long idUser){
        UserEntity user = userRepository.findById(idUser).get();
        user.addFavoriteMovie(idMovie);
        userRepository.save(user);
    }

    public void removeFavoriteMovie(Long idMovie, Long idUser){
        UserEntity user = userRepository.findById(idUser).get();
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
    }

}
