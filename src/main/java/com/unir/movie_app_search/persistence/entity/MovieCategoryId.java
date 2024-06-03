package com.unir.movie_app_search.persistence.entity;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor(force = true)
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class MovieCategoryId implements Serializable {

    private final Integer idMovie;
    private final Integer idCategory;

}
