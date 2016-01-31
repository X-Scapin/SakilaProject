package isep.web.sakila.dao.repositories;

import org.springframework.data.repository.CrudRepository;

import isep.web.sakila.jpa.entities.City;

public interface CityRepository extends CrudRepository<City, Integer> {

}
