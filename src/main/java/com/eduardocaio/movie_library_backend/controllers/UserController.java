package com.eduardocaio.movie_library_backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eduardocaio.movie_library_backend.dto.MovieDTO;
import com.eduardocaio.movie_library_backend.dto.UserDTO;
import com.eduardocaio.movie_library_backend.dto.UserSignupDTO;
import com.eduardocaio.movie_library_backend.services.UserService;

@RestController
@CrossOrigin
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {
        List<UserDTO> users = userService.findAll();
        return ResponseEntity.ok().body(users);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable("id") Long id) {
        UserDTO user = userService.findById(id);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping
    public ResponseEntity<Void> signup(@RequestBody UserSignupDTO user){
        userService.signup(user);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<UserDTO> update(@RequestBody UserDTO userDTO) {
        UserDTO user = userService.update(userDTO);
        return ResponseEntity.ok().body(user);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        userService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/{username}/remove-movie/{idMovie}")
    public ResponseEntity<Void> removeFavoriteMovie(@PathVariable("username") String username, @PathVariable("idMovie") Long idMovie){
        userService.removeFavoriteMovie(idMovie, username);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/{username}/add-movie/{idMovie}")
    public ResponseEntity<Void> addFavoriteMovie(@PathVariable("username") String username, @PathVariable("idMovie") Long idMovie){
        userService.addFavoriteMovie(idMovie, username);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/{username}/favorite-movies")
    public ResponseEntity<List<MovieDTO>> userFavoriteMovies(@PathVariable("username") String username){
        return ResponseEntity.ok().body(userService.userFavoriteMovies(username));
    }

}
