package isep.web.sakila.webapi.service;

import java.util.List;

import isep.web.sakila.webapi.model.InventoryWO;

public interface InventoryService {
	InventoryWO findById(int id);

	void saveInventory(InventoryWO inventoryWO);

	void updateInventory(InventoryWO inventoryWO);

	void deleteInventoryById(int id);

	List<InventoryWO> findAllInventories();
}
