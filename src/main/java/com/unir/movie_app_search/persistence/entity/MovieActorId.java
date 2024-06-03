package com.unir.movie_app_search.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class MovieActorId implements Serializable {

    private Integer idMovie;
    private Integer idActor;

    public MovieActorId (Integer idMovie, Integer idActor) {
        this.idMovie = idMovie;
        this.idActor = idActor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MovieActorId that)) return false;
        return Objects.equals(idMovie, that.idMovie) &&
                Objects.equals(idActor, that.idActor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMovie, idActor);
    }

}
