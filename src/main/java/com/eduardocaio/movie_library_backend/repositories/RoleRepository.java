package com.eduardocaio.movie_library_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eduardocaio.movie_library_backend.entities.RoleEntity;
import java.util.Optional;


public interface RoleRepository extends JpaRepository<RoleEntity, Long>{

    Optional<RoleEntity> findByDescription(String description);

}
