package com.unir.movie_app_search.persistence.repository;

import com.unir.movie_app_search.persistence.entity.CategoryEntity;
import org.springframework.data.repository.ListCrudRepository;

public interface CategoryRepository extends ListCrudRepository<CategoryEntity, Integer> {
}
