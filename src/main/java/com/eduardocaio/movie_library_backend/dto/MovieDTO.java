package com.eduardocaio.movie_library_backend.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class MovieDTO {

    private Long id;

    private String title;

    private String overview;

    private String poster_path;

    private String backdrop_path;

    private LocalDate release_date;

    private Double vote_average;

    private List<ActorDTO> actors = new ArrayList<>();

    public void addActor(ActorDTO actor){
        actors.add(actor);
    }

}
