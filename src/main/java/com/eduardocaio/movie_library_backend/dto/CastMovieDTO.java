package com.eduardocaio.movie_library_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CastMovieDTO {

    private Long id;
    private List<ActorDTO> cast;

    public void removePerson(ActorDTO actor){
        cast.remove(actor);
    }

}
