package com.eduardocaio.movie_library_backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eduardocaio.movie_library_backend.dto.UserDTO;
import com.eduardocaio.movie_library_backend.entities.UserEntity;
import com.eduardocaio.movie_library_backend.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserDTO> findAll(){
        List<UserEntity> users = userRepository.findAll();
        return users.stream().map(UserDTO::new).toList();
    }

    public UserDTO findById(Long id){
        UserDTO user = new UserDTO(userRepository.findById(id).get());
        return user;
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

    private void updateData(UserDTO userDTO, UserEntity userEntity){
        if(userDTO.getName() != null){
            userEntity.setName(userDTO.getName());
        }
    }

}
