package com.eduardocaio.movie_library_backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eduardocaio.movie_library_backend.entities.RoleEntity;
import com.eduardocaio.movie_library_backend.repositories.RoleRepository;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public RoleEntity findByDescription(String description){
        RoleEntity role = roleRepository.findByDescription(description).get();
        return role;
    }

}
