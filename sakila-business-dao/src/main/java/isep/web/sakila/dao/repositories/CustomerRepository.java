package isep.web.sakila.dao.repositories;

import org.springframework.data.repository.CrudRepository;

import isep.web.sakila.jpa.entities.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {

}
