package com.eduardocaio.movie_library_backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eduardocaio.movie_library_backend.dto.MovieDTO;
import com.eduardocaio.movie_library_backend.services.MovieApiService;

@RestController
@RequestMapping(value = "/movies")
public class MovieController {

    @Autowired
    private MovieApiService movieApiService;

    @GetMapping
    public ResponseEntity<List<MovieDTO>> discover(){
        return ResponseEntity.ok().body(movieApiService.discoverMovie());
    }

    @GetMapping(value = "/search={query}")
    public ResponseEntity<List<MovieDTO>> searchMovie(@PathVariable("query") String query){
        return ResponseEntity.ok().body(movieApiService.searchMovie(query));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<MovieDTO> findMovieId(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(movieApiService.findMovie(id));
    }

}
