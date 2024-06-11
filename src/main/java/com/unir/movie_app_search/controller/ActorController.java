package com.unir.movie_app_search.controller;

import com.unir.movie_app_search.persistence.entity.ActorEntity;
import com.unir.movie_app_search.persistence.entity.MovieEntity;
import com.unir.movie_app_search.service.ActorService;
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
public class ActorController {
    private final ActorService actorService;

    @GetMapping("/actores")
    @Operation(
            operationId = "Obtener actores",
            description = "Operacion de lectura",
            summary = "Se devuelve una lista de todos las actores almacenados en la base de datos.")
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ActorEntity.class)))
    public ResponseEntity<List<ActorEntity>> getAll() {
        List<ActorEntity> movies = actorService.getAll();
        return ResponseEntity.ok(Objects.requireNonNullElse(movies, Collections.emptyList()));
    }

    @GetMapping("/actores/{idActor}")
    @Operation(
            operationId = "Obtener pelicula por id",
            description = "Operacion de lectura",
            summary = "Se devuelve una pelicula almacenada en la base de datos.")
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ActorEntity.class)))
    public ResponseEntity<ActorEntity> get(@PathVariable int idActor) {
        return ResponseEntity.ok(this.actorService.get(idActor));
    }

    @PostMapping("/actores")
    @Operation(
            operationId = "Insertar un actor",
            description = "Operacion de escritura",
            summary = "Se crea un actor a partir de sus datos.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos del actor a crear.",
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
            description = "No se ha encontrado el actor con el identificador indicado.")
    public ResponseEntity<ActorEntity> add(@RequestBody ActorEntity movie) {
        if (movie.getId() == null || !this.actorService.exists(movie.getId())) {
            return ResponseEntity.ok(this.actorService.save(movie));
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/actores/{id}")
    @Operation(
            operationId = "Modificar totalmente un actor",
            description = "Operacion de escritura",
            summary = "Se modifica totalmente un actor.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos del actor a actualizar.",
                    required = true,
                    content = @Content(mediaType = "application/merge-patch+json", schema = @Schema(implementation = MovieEntity.class))))
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = MovieEntity.class)))
    @ApiResponse(
            responseCode = "404",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
            description = "Actor no encontrada.")
    public ResponseEntity<ActorEntity> update(@PathVariable Integer id, @RequestBody ActorEntity movie) {
        if (id != null && actorService.exists(id)) {
            movie.setId(id);
            return ResponseEntity.ok(actorService.save(movie));
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/actores/{idActor}")
    @Operation(
            operationId = "Eliminar un actor",
            description = "Operacion de escritura",
            summary = "Se elimina una actor a partir de su identificador.")
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)))
    @ApiResponse(
            responseCode = "404",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)),
            description = "No se ha encontrado la actor con el identificador indicado.")
    public ResponseEntity<Void> delete(@PathVariable int idActor) {
        if (this.actorService.exists(idActor)) {
            this.actorService.delete(idActor);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
