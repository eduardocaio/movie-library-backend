package com.eduardocaio.movie_library_backend.dto;

import org.springframework.beans.BeanUtils;

import com.eduardocaio.movie_library_backend.entities.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDTO {

    private Long id;

    private String name;

    private String email;

    public UserDTO(UserEntity user){
        BeanUtils.copyProperties(user, this);
    }

}
