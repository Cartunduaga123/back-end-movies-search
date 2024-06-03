package com.unir.movie_app_search.service;

import com.unir.movie_app_search.persistence.entity.MovieEntity;
import com.unir.movie_app_search.persistence.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<MovieEntity> getAll() {
        return this.movieRepository.findAll();
    }

    public MovieEntity get(int idPizza) {
        return this.movieRepository.findById(idPizza).orElse(null);
    }

    public void save(MovieEntity movieEntity) {
        this.movieRepository.save(movieEntity);
    }

    public void delete(int idPizza) {
        this.movieRepository.deleteById(idPizza);
    }

/*    public List<MovieEntity> getByTitle(String title) {
        //this.movieRepository.findAll().contains( )
    }*/


}
