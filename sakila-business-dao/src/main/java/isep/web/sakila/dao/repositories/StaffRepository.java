package isep.web.sakila.dao.repositories;

import org.springframework.data.repository.CrudRepository;

import isep.web.sakila.jpa.entities.Staff;

public interface StaffRepository extends CrudRepository<Staff, Integer> {

}
