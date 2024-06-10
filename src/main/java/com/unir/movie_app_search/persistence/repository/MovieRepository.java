package com.unir.movie_app_search.persistence.repository;

import com.unir.movie_app_search.persistence.entity.MovieEntity;
import org.springframework.data.repository.ListCrudRepository;




public interface MovieRepository extends ListCrudRepository<MovieEntity, Integer>, MovieRepositoryCustom {

}
