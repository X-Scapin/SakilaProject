package isep.web.sakila.webapi.service;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import isep.web.sakila.dao.repositories.FilmRepository;
import isep.web.sakila.dao.repositories.InventoryRepository;
import isep.web.sakila.dao.repositories.StoreRepository;
import isep.web.sakila.jpa.entities.Film;
import isep.web.sakila.jpa.entities.Inventory;
import isep.web.sakila.jpa.entities.Store;
import isep.web.sakila.webapi.model.InventoryWO;

@Service("inventoryService")
@Transactional
public class InventoryServiceImpl implements InventoryService {
	@Autowired
	private InventoryRepository inventoryRepository;
	@Autowired
	private FilmRepository filmRepository;
	@Autowired
	private StoreRepository storeRepository;

	private static final Log log = LogFactory.getLog(InventoryServiceImpl.class);

	
	@Override
	public InventoryWO findById(int id) {
		log.debug("Find inventory with id : "+id);
		Inventory inventory = inventoryRepository.findOne(id);
		
		if (inventory != null) {
			return new InventoryWO(inventory);
		}
		return null;
	}

	@Override
	public void saveInventory(InventoryWO userWO) {
		Inventory inventory = new Inventory();
		
		Film film = filmRepository.findOne(userWO.getFilm());
		Store store = storeRepository.findOne(userWO.getStore());
		
		inventory.setFilm(film);
		inventory.setStore(store);
		inventory.setLastUpdate(new Timestamp(System.currentTimeMillis()));
		inventoryRepository.save(inventory);
	}

	@Override
	public void updateInventory(InventoryWO userWO) {
		Inventory inventory = inventoryRepository.findOne(userWO.getInventoryId());
		Film film = filmRepository.findOne(userWO.getFilm());
		Store store = storeRepository.findOne(userWO.getStore());
		
		inventory.setFilm(film);
		inventory.setStore(store);
		inventory.setLastUpdate(new Timestamp(System.currentTimeMillis()));
		inventoryRepository.save(inventory);
	}

	@Override
	public void deleteInventoryById(int id) {
		inventoryRepository.delete(id);
	}

	@Override
	public List<InventoryWO> findAllInventories() {
		List<InventoryWO> inventories = new LinkedList<InventoryWO>();
		for(Inventory inventory : inventoryRepository.findAll()){
			inventories.add(new InventoryWO(inventory));
			log.debug("Add inventory with id : "+inventory.getInventoryId());
		}
		return inventories;
	}

}
