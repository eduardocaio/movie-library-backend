package com.eduardocaio.movie_library_backend.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class MovieResponseDTO {

    private int page;
    private List<MovieDTO> results;
    private int totalPages;
    private int totalResults;

}
