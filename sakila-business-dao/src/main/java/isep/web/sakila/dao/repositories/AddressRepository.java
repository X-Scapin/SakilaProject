package isep.web.sakila.dao.repositories;

import org.springframework.data.repository.CrudRepository;

import isep.web.sakila.jpa.entities.Address;

public interface AddressRepository extends CrudRepository<Address, Integer> {

}
