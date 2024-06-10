package com.unir.movie_app_search.persistence.repository;

import com.unir.movie_app_search.persistence.entity.MovieEntity;

import java.util.List;

public interface MovieRepositoryCustom {
    List<MovieEntity> findMovies(String nombre, String director, Integer anoPublicacion, String sinopsis,
                                 String criticas, Integer duracion, Integer puntuacion, String lenguaje,
                                 Double precio, Double precioRenta);
}