package isep.web.sakila.dao.repositories;

import org.springframework.data.repository.CrudRepository;

import isep.web.sakila.jpa.entities.Rental;

public interface RentalRepository extends CrudRepository<Rental, Integer> {

}
