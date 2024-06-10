package com.unir.movie_app_search.persistence.repository;

import com.unir.movie_app_search.persistence.entity.MovieEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class MovieRepositoryCustomImpl implements MovieRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<MovieEntity> findMovies(String nombre, String director, Integer anoPublicacion, String sinopsis,
                                        String criticas, Integer duracion, Integer puntuacion, String lenguaje,
                                        Double precio, Double precioRenta) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<MovieEntity> query = cb.createQuery(MovieEntity.class);
        Root<MovieEntity> movie = query.from(MovieEntity.class);

        List<Predicate> predicates = new ArrayList<>();

        if (nombre != null) {
            predicates.add(cb.like(cb.lower(movie.get("nombre")), "%" + nombre.toLowerCase() + "%"));
        }
        if (director != null) {
            predicates.add(cb.like(cb.lower(movie.get("director")), "%" + director.toLowerCase() + "%"));
        }
        if (anoPublicacion != null) {
            predicates.add(cb.equal(movie.get("anoPublicacion"), anoPublicacion));
        }
        if (sinopsis != null) {
            predicates.add(cb.like(cb.lower(movie.get("sinopsis")), "%" + sinopsis.toLowerCase() + "%"));
        }
        if (criticas != null) {
            predicates.add(cb.like(cb.lower(movie.get("criticas")), "%" + criticas.toLowerCase() + "%"));
        }
        if (duracion != null) {
            predicates.add(cb.lessThanOrEqualTo(movie.get("duracion"), duracion));
        }
        if (puntuacion != null) {
            predicates.add(cb.lessThanOrEqualTo(movie.get("puntuacion"), puntuacion));
        }
        if (lenguaje != null) {
            predicates.add(cb.like(cb.lower(movie.get("lenguaje")), "%" + lenguaje.toLowerCase() + "%"));
        }
        if (precio != null) {
            predicates.add(cb.lessThanOrEqualTo(movie.get("precio"), precio));
        }
        if (precioRenta != null) {
            predicates.add(cb.lessThanOrEqualTo(movie.get("precioRenta"), precioRenta));
        }

        query.where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(query).getResultList();
    }
}
