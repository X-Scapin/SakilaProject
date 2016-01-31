package isep.web.sakila.dao.repositories;

import org.springframework.data.repository.CrudRepository;

import isep.web.sakila.jpa.entities.Film;

public interface FilmRepository extends CrudRepository<Film, Integer> {

}
