package com.eduardocaio.movie_library_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eduardocaio.movie_library_backend.entities.VerificationUserEntity;
import java.util.UUID;


public interface VerificationUserRepository extends JpaRepository<VerificationUserEntity, UUID>{


}
