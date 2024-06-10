package com.unir.movie_app_search.controller;

import com.unir.movie_app_search.persistence.entity.MovieEntity;
import com.unir.movie_app_search.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
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
    public ResponseEntity<List<MovieEntity>> getAll(
            @RequestHeader Map<String, String> headers,
            @Parameter(name = "nombre", description = "Nombre de la pelicula. No tiene por que ser exacto", example = "matrix", required = false)
            @RequestParam(required = false) String nombre,
            @Parameter(name = "director", description = "Nombre del director. No tiene por que ser exacto", example = "jhon", required = false)
            @RequestParam(required = false) String director,
            @Parameter(name = "anoPublicacion", description = "Año de publicación. Año valido", example = "2022", required = false)
            @RequestParam(required = false) Integer anoPublicacion,
            @Parameter(name = "sinopsis", description = "Descripscion de la pelicula. No tiene por que ser exacto", example = "Era se una vez", required = false)
            @RequestParam(required = false) String sinopsis,
            @Parameter(name = "criticas", description = "Critica. No tiene por que ser exacto", example = "Buena", required = false)
            @RequestParam(required = false) String criticas,
            @Parameter(name = "duracion", description = "Valor en minutos. No tiene por que ser exacto", example = "60", required = false)
            @RequestParam(required = false) Integer duracion,
            @Parameter(name = "puntuacion", description = "Puntuacion. No tiene por que ser exacto", example = "10", required = false)
            @RequestParam(required = false) Integer puntuacion,
            @Parameter(name = "lenguaje", description = "Lenguaje. No tiene por que ser exacto", example = "Español", required = false)
            @RequestParam(required = false) String lenguaje,
            @Parameter(name = "precio", description = "Precio compra. No tiene por que ser exacto", example = "8.50", required = false)
            @RequestParam(required = false) Double precio,
            @Parameter(name = "precioRenta", description = "Precio renta. No tiene por que ser exacto", example = "4.50", required = false)
            @RequestParam(required = false) Double precioRenta
    ) {
        List<MovieEntity> movies = movieService.getAll(nombre, director, anoPublicacion, sinopsis, criticas, duracion, puntuacion, lenguaje, precio, precioRenta);
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
    @Operation(
            operationId = "Insertar una pelicula",
            description = "Operacion de escritura",
            summary = "Se crea una pelicula a partir de sus datos.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos de la pelicula a crear.",
                    required = true,
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = MovieEntity.class))))
    @ApiResponse(
            responseCode = "201",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = MovieEntity.class)))
    @ApiResponse(
            responseCode = "400",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
            description = "Datos incorrectos introducidos.")
    @ApiResponse(
            responseCode = "404",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
            description = "No se ha encontrado la pelicula con el identificador indicado.")
    public ResponseEntity<MovieEntity> add(@RequestBody MovieEntity movie) {
        if (movie.getId() == null || !this.movieService.exists(movie.getId())) {
            return ResponseEntity.ok(this.movieService.save(movie));
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/peliculas/{id}")
    @Operation(
            operationId = "Modificar totalmente una pelicula",
            description = "Operacion de escritura",
            summary = "Se modifica totalmente una pelicula.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos de la pelicula a actualizar.",
                    required = true,
                    content = @Content(mediaType = "application/merge-patch+json", schema = @Schema(implementation = MovieEntity.class))))
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = MovieEntity.class)))
    @ApiResponse(
            responseCode = "404",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
            description = "Pelicula no encontrada.")
    public ResponseEntity<MovieEntity> update(@PathVariable Integer id, @RequestBody MovieEntity movie) {
        if (id != null && movieService.exists(id)) {
            movie.setId(id);
            return ResponseEntity.ok(movieService.save(movie));
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/peliculas/{idMovie}")
    @Operation(
            operationId = "Eliminar una pelicula",
            description = "Operacion de escritura",
            summary = "Se elimina una pelicula a partir de su identificador.")
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)))
    @ApiResponse(
            responseCode = "404",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
            description = "No se ha encontrado la pelicula con el identificador indicado.")
    public ResponseEntity<Void> delete(@PathVariable int idMovie) {
        if (this.movieService.exists(idMovie)) {
            this.movieService.delete(idMovie);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/peliculas/exist/{idMovie}")
    @Operation(
            operationId = "Obtener pelicula por id",
            description = "Operacion de lectura",
            summary = "Se devuelve un true o false si la pelicula existe en la base de datos.")
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = MovieEntity.class)))
    public ResponseEntity<Boolean> exist(@PathVariable int idMovie) {
        return ResponseEntity.ok(this.movieService.exists(idMovie));
    }


}
