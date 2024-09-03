package com.eduardocaio.movie_library_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eduardocaio.movie_library_backend.entities.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);

}
