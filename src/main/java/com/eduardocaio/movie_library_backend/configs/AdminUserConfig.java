package com.eduardocaio.movie_library_backend.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.eduardocaio.movie_library_backend.entities.RoleEntity;
import com.eduardocaio.movie_library_backend.entities.UserEntity;
import com.eduardocaio.movie_library_backend.enums.StatusUser;
import com.eduardocaio.movie_library_backend.repositories.RoleRepository;
import com.eduardocaio.movie_library_backend.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Configuration
public class AdminUserConfig implements CommandLineRunner{

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        RoleEntity role = roleRepository.save(new RoleEntity(null, "ADMIN"));
        RoleEntity role2 = roleRepository.save(new RoleEntity(null, "BASIC"));

        UserEntity user = new UserEntity();
        user.setName("Administrator");
        user.setUsername("admin");
        user.setPassword(passwordEncoder.encode("12345"));
        user.setEmail("admin@test.com");
        user.addRole(role);
        user.addRole(role2);
        user.setStatus(StatusUser.ATIVO);
        userRepository.save(user);

    }

}
