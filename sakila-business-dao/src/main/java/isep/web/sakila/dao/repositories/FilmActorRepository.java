package isep.web.sakila.dao.repositories;

import org.springframework.data.repository.CrudRepository;

import isep.web.sakila.jpa.entities.FilmActor;

public interface FilmActorRepository extends CrudRepository<FilmActor, Integer> {

}