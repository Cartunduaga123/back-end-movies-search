package com.unir.movie_app_search.service;

import com.unir.movie_app_search.persistence.entity.MovieEntity;
import com.unir.movie_app_search.persistence.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<MovieEntity> getAll(String nombre, String director, Integer anoPublicacion, String sinopsis, String criticas, Integer duracion, Integer puntuacion, String lenguaje, Double precio, Double precioRenta) {
        return movieRepository.findMovies(nombre, director, anoPublicacion, sinopsis, criticas, duracion, puntuacion, lenguaje, precio, precioRenta);
    }

    public MovieEntity get(int idMovie) {
        return this.movieRepository.findById(idMovie).orElse(null);
    }

    public MovieEntity save(MovieEntity movieEntity) {
        this.movieRepository.save(movieEntity);
        return movieEntity;
    }

    public void delete(int idMovie) {
        this.movieRepository.deleteById(idMovie);
    }

    public boolean exists(int idMovie) {
        return this.movieRepository.existsById(idMovie);
    }

}
