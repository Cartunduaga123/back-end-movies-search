package com.unir.movie_app_search.persistence.entity;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor(force = true)
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class MovieActorId implements Serializable {

    private Integer idMovie;
    private Integer idActor;

}
