package com.unir.movie_app_search.persistence.repository;

import com.unir.movie_app_search.persistence.entity.ActorEntity;
import org.springframework.data.repository.ListCrudRepository;

public interface ActorRepository extends ListCrudRepository<ActorEntity, Integer> {
}
