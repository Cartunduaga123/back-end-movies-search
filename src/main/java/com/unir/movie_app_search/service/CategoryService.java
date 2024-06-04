package com.unir.movie_app_search.service;

import com.unir.movie_app_search.persistence.entity.CategoryEntity;
import com.unir.movie_app_search.persistence.entity.MovieEntity;
import com.unir.movie_app_search.persistence.repository.CategoryRepository;
import com.unir.movie_app_search.persistence.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryEntity> getAll() {
        return this.categoryRepository.findAll();
    }

    public CategoryEntity get(int idCategory) {
        return this.categoryRepository.findById(idCategory).orElse(null);
    }

    public CategoryEntity save(CategoryEntity categoryEntity) {
        this.categoryRepository.save(categoryEntity);
        return categoryEntity;
    }

    public void delete(int idCategory) {
        this.categoryRepository.deleteById(idCategory);
    }

    public boolean exists(int idMovie) {
        return this.categoryRepository.existsById(idMovie);
    }
}
