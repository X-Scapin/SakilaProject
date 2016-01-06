package isep.web.sakila.dao.repositories;

import org.springframework.data.repository.CrudRepository;

import isep.web.sakila.jpa.entities.Actor;

public interface ActorRepository extends CrudRepository<Actor, Integer> {

}
