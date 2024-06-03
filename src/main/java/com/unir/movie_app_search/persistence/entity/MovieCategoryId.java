package com.unir.movie_app_search.persistence.entity;

import java.io.Serializable;
import java.util.Objects;

public class MovieCategoryId implements Serializable {

    private final Integer idMovie;
    private final Integer idCategory;

    public MovieCategoryId(Integer idMovie, Integer idCategory) {
        this.idMovie = idMovie;
        this.idCategory = idCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MovieCategoryId that)) return false;
        return Objects.equals(idMovie, that.idMovie) &&
                Objects.equals(idCategory, that.idCategory);
    }

    @Override
    public int hashCode() {
        return  Objects.hash(idMovie, idCategory);
    }

}
