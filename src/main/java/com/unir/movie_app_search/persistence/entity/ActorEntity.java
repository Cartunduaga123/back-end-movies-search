package com.unir.movie_app_search.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Table(name="actores")
@Getter
@Setter
@NoArgsConstructor
public class ActorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(name = "nombre_personaje", nullable = false, length = 100)
    private String nombrePersonaje;

    @Column(name = "img_url", nullable = true, length = 255)
    private String imgUrl;

    @OneToMany(mappedBy = "actor")
    private List<MovieActorEntity> peliculaActores;
}
