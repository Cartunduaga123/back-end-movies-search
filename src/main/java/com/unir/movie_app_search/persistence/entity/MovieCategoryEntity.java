package com.unir.movie_app_search.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="pelicula_categoria")
@IdClass(MovieCategoryId.class)
@Getter
@Setter
@NoArgsConstructor
public class MovieCategoryEntity {

    @Id
    @Column(name = "pelicula_id", nullable = false)
    private Integer idMovie;

    @Id
    @Column(name = "categoria_id", nullable = false)
    private Integer idCategory;

    @ManyToOne
    @JoinColumn(name = "pelicula_id", referencedColumnName = "id", insertable = false, updatable = false)
    private MovieEntity pelicula;

    @ManyToOne
    @JoinColumn(name = "categoria_id", referencedColumnName = "id", insertable = false, updatable = false)
    private CategoryEntity categoria;
}
