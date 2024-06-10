package com.unir.movie_app_search.persistence.repository;

import com.unir.movie_app_search.persistence.entity.MovieEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieRepository extends ListCrudRepository<MovieEntity, Integer> {
    List<MovieEntity> findAll();
    @Query(value = "SELECT * FROM movie_entity " +
            "WHERE (:nombre IS NULL OR LOWER(nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))) " +
            "AND (:director IS NULL OR LOWER(director) LIKE LOWER(CONCAT('%', :director, '%'))) " +
            "AND (:anoPublicacion IS NULL OR ano_publicacion = :anoPublicacion) " +
            "AND (:sinopsis IS NULL OR LOWER(sinopsis) LIKE LOWER(CONCAT('%', :sinopsis, '%'))) " +
            "AND (:criticas IS NULL OR LOWER(criticas) LIKE LOWER(CONCAT('%', :criticas, '%'))) " +
            "AND (:duracion IS NULL OR duracion <= :duracion) " +
            "AND (:puntuacion IS NULL OR puntuacion <= :puntuacion) " +
            "AND (:lenguaje IS NULL OR LOWER(lenguaje) LIKE LOWER(CONCAT('%', :lenguaje, '%'))) " +
            "AND (:precio IS NULL OR precio <= :precio) " +
            "AND (:precioRenta IS NULL OR precio_renta <= :precioRenta)",
            nativeQuery = true)
    List<MovieEntity> findMoviesByFilter(
            @Param("nombre") String nombre,
            @Param("director") String director,
            @Param("anoPublicacion") Integer anoPublicacion,
            @Param("sinopsis") String sinopsis,
            @Param("criticas") String criticas,
            @Param("duracion") Integer duracion,
            @Param("puntuacion") Integer puntuacion,
            @Param("lenguaje") String lenguaje,
            @Param("precio") Double precio,
            @Param("precioRenta") Double precioRenta
    );

/*
    @Query("SELECT m FROM MovieEntity m WHERE " +
            "(:nombre IS NULL OR LOWER(m.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))) AND " +
            "(:director IS NULL OR LOWER(m.director) LIKE LOWER(CONCAT('%', :director, '%'))) AND " +
            "(:anoPublicacion IS NULL OR m.anoPublicacion = :anoPublicacion) AND " +
            "(:sinopsis IS NULL OR LOWER(m.sinopsis) LIKE LOWER(CONCAT('%', :sinopsis, '%'))) AND " +
            "(:criticas IS NULL OR LOWER(m.criticas) LIKE LOWER(CONCAT('%', :criticas, '%'))) AND " +
            "(:duracion IS NULL OR m.duracion <= :duracion) AND " +
            "(:puntuacion IS NULL OR m.puntuacion <= :puntuacion) AND " +
            "(:lenguaje IS NULL OR LOWER(m.lenguaje) LIKE LOWER(CONCAT('%', :lenguaje, '%'))) AND " +
            "(:precio IS NULL OR m.precio <= :precio) AND " +
            "(:precioRenta IS NULL OR m.precioRenta <= :precioRenta)")

    List<MovieEntity> findMoviesByFilter(String nombre, String director, String anoPublicacion, String sinopsis, String criticas, String duracion, String puntuacion, String lenguaje, String precio, String precioRenta);
*/

}
