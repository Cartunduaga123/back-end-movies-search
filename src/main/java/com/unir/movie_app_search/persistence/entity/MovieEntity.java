package com.unir.movie_app_search.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name="peliculas")
@Getter
@Setter
@NoArgsConstructor
public class MovieEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(nullable = false, length = 255)
    private String nombre;

    @Column(nullable = false, length = 255)
    private String director;

    @Column(name = "ano_publicacion", nullable = false, columnDefinition = "Integer(4)")
    private Integer anoPublicacion;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String sinopsis;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String criticas;

    @Column( nullable = false, columnDefinition = "Integer(4)")
    private Integer duracion;

    @Column(name="trailer_url", nullable = true, length = 255)
    private String trailerUrl;

    @Column( nullable = false, columnDefinition = "Integer(4)")
    private Integer puntuacion;

    @Column(nullable = false, length = 50)
    private String lenguaje;

    @Column(name="poster_url", nullable = true, length = 255)
    private String posterUrl;

    @Column(name="backdrop_url", nullable = true, length = 255)
    private String backdropUrl;

    @Column( nullable = false, columnDefinition = "Decimal(5,2) default '9.99'")
    private Double precio;

    @Column(name="precio_renta", nullable = false, columnDefinition = "Decimal(5,2) default '9.99'")
    private Double precioRenta;

    @OneToMany(mappedBy = "pelicula")
    @JsonIgnore
    private Set<MovieActorEntity> peliculaActores;

    @OneToMany(mappedBy = "pelicula")
    @JsonIgnore
    private Set<MovieCategoryEntity> peliculaCategorias;

}
