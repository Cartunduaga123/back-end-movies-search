package com.unir.movie_app_search.service;

import com.unir.movie_app_search.persistence.entity.ActorEntity;
import com.unir.movie_app_search.persistence.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActorService {

    private final ActorRepository actorRepository;

    @Autowired
    public ActorService(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    public List<ActorEntity> getAll() {
        return this.actorRepository.findAll();
    }

    public ActorEntity get(int idActor) {
        return this.actorRepository.findById(idActor).orElse(null);
    }

    public ActorEntity save(ActorEntity actorEntity) {
        this.actorRepository.save(actorEntity);
        return actorEntity;
    }

    public void delete(int idActor) {
        this.actorRepository.deleteById(idActor);
    }

    public boolean exists(int idMovie) {
        return this.actorRepository.existsById(idMovie);
    }
}
