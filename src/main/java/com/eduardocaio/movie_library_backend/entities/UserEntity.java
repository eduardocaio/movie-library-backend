package com.eduardocaio.movie_library_backend.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;

import com.eduardocaio.movie_library_backend.dto.UserDTO;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class UserEntity implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @ElementCollection
    @CollectionTable(name = "tb_user_favorite_movies", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "movie_id")
    private Set<Long> favoriteMovies = new HashSet<>();

    public UserEntity(UserDTO user){
        BeanUtils.copyProperties(user, this);
    }

    public void addFavoriteMovie(Long id){
        favoriteMovies.add(id);
    }

}
