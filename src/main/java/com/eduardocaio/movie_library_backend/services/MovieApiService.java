package com.eduardocaio.movie_library_backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.eduardocaio.movie_library_backend.dto.MovieDTO;
import com.eduardocaio.movie_library_backend.dto.MovieResponseDTO;

@Service
public class MovieApiService {

    @Value("${tmdb.api.key}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    public MovieDTO findMovie(Long id) {
      
        String url = "https://api.themoviedb.org/3/movie/" + id + "?api_key=" + apiKey + "&language=pt-BR";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<MovieDTO> response = restTemplate.exchange(url, HttpMethod.GET, entity, MovieDTO.class);

        return response.getBody();

    }

    public List<MovieDTO> discoverMovie() {
  
        String url = "https://api.themoviedb.org/3/discover/movie?api_key=" + apiKey + "&language=pt-BR";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<MovieResponseDTO> response = restTemplate.exchange(url, HttpMethod.GET, entity, MovieResponseDTO.class);

        return response.getBody().getResults();
    }

    public List<MovieDTO> searchMovie(String query){

        String url = "https://api.themoviedb.org/3/search/movie?api_key="+ apiKey +"&query="+ query +"&include_adult=false&language=pt-BR&page=1";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<MovieResponseDTO> response = restTemplate.exchange(url, HttpMethod.GET, entity, MovieResponseDTO.class);

        return response.getBody().getResults();
    }

}
