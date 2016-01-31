package isep.web.sakila.dao.repositories;

import org.springframework.data.repository.CrudRepository;

import isep.web.sakila.jpa.entities.Store;

public interface StoreRepository extends CrudRepository<Store, Byte> {

}
