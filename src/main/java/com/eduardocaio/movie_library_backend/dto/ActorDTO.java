package com.eduardocaio.movie_library_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ActorDTO {
    
    private Long id;
    private String name;
    private String known_for_department;
    private String profile_path;
    private String character;

}
