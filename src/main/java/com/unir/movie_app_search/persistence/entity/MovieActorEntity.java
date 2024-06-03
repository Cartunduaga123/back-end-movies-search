package com.unir.movie_app_search.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="pelicula_actor")
@IdClass(MovieActorId.class)
@Getter
@Setter
@NoArgsConstructor
public class MovieActorEntity {

    @Id
    @Column(name = "pelicula_id", nullable = false)
    private Integer movieId;

    @Id
    @Column(name = "actor_id", nullable = false)
    private Integer actorId;

    @ManyToOne
    @JoinColumn(name = "pelicula_id", referencedColumnName = "id", insertable = false, updatable = false)
    private MovieEntity pelicula;

    @ManyToOne
    @JoinColumn(name = "actor_id", referencedColumnName = "id", insertable = false, updatable = false)
    private ActorEntity actor;

}
