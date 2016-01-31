package isep.web.sakila.dao.repositories;

import org.springframework.data.repository.CrudRepository;

import isep.web.sakila.jpa.entities.Inventory;

public interface InventoryRepository extends CrudRepository<Inventory, Integer> {

}
