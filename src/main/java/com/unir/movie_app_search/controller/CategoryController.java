package com.unir.movie_app_search.controller;

import com.unir.movie_app_search.persistence.entity.CategoryEntity;
import com.unir.movie_app_search.service.CategoryService;
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
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/categorias")
    @Operation(
            operationId = "Obtener categorias",
            description = "Operacion de lectura",
            summary = "Se devuelve una lista de todos las categorias almacenadas en la base de datos.")
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CategoryEntity.class)))
    public ResponseEntity<List<CategoryEntity>> getAll() {
        List<CategoryEntity> movies = categoryService.getAll();
        return ResponseEntity.ok(Objects.requireNonNullElse(movies, Collections.emptyList()));
    }

    @GetMapping("/categorias/{idCategory}")
    @Operation(
            operationId = "Obtener una categoria por id",
            description = "Operacion de lectura",
            summary = "Se devuelve una categoria almacenada en la base de datos.")
    @ApiResponse(
            responseCode = "200",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CategoryEntity.class)))
    public ResponseEntity<CategoryEntity> get(@PathVariable int idCategory) {
        return ResponseEntity.ok(this.categoryService.get(idCategory));
    }

    @PostMapping("/categorias")
    public ResponseEntity<CategoryEntity> add(@RequestBody CategoryEntity movie) {
        if (movie.getId() == null || !this.categoryService.exists(movie.getId())) {
            return ResponseEntity.ok(this.categoryService.save(movie));
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/categorias/{id}")
    public ResponseEntity<CategoryEntity> update(@PathVariable Integer id, @RequestBody CategoryEntity movie) {
        if (id != null && categoryService.exists(id)) {
            movie.setId(id);
            return ResponseEntity.ok(categoryService.save(movie));
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/categorias/{idCategory}")
    public ResponseEntity<Void> delete(@PathVariable int idCategory) {
        if (this.categoryService.exists(idCategory)) {
            this.categoryService.delete(idCategory);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
