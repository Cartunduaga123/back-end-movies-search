package com.unir.movie_app_search.service;

import com.unir.movie_app_search.persistence.entity.MovieEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MovieService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<MovieEntity> getAll() {
        return this.jdbcTemplate.query("SELECT * FROM peliculas", new BeanPropertyRowMapper<>(MovieEntity.class));
    }

}
