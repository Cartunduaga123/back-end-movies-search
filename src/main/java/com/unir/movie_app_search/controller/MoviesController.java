package com.unir.movie_app_search.controller;

import com.unir.movie_app_search.persistence.entity.MovieEntity;
import com.unir.movie_app_search.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MoviesController {

    private final MovieService movieService;

    @GetMapping("/peliculas")
    @Operation(
            operationId = "Obtener peliculas",
            description = "Operacion de lectura",
            summary = "Se devuelve una lista de todos las peliculas almacenados en la base de datos.")
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = MovieEntity.class)))
    public ResponseEntity<List<MovieEntity>> getAll() {
        List<MovieEntity> movies = movieService.getAll();
        return ResponseEntity.ok(Objects.requireNonNullElse(movies, Collections.emptyList()));
    }

    @GetMapping("/peliculas/{idMovie}")
    @Operation(
            operationId = "Obtener pelicula por id",
            description = "Operacion de lectura",
            summary = "Se devuelve una pelicula almacenada en la base de datos.")
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = MovieEntity.class)))
    public ResponseEntity<MovieEntity> get(@PathVariable int idMovie) {
        return ResponseEntity.ok(this.movieService.get(idMovie));
    }

    @PostMapping("/peliculas")
    public ResponseEntity<MovieEntity> add(@RequestBody MovieEntity movie) {
        if (movie.getId() == null || !this.movieService.exists(movie.getId())) {
            return ResponseEntity.ok(this.movieService.save(movie));
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/peliculas/{id}")
    public ResponseEntity<MovieEntity> update(@PathVariable Integer id, @RequestBody MovieEntity movie) {
        if (id != null && movieService.exists(id)) {
            movie.setId(id);
            return ResponseEntity.ok(movieService.save(movie));
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/peliculas/{idMovie}")
    public ResponseEntity<Void> delete(@PathVariable int idMovie) {
        if (this.movieService.exists(idMovie)) {
            this.movieService.delete(idMovie);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

}
