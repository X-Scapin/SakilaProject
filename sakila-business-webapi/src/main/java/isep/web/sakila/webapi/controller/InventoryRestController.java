package isep.web.sakila.webapi.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import isep.web.sakila.webapi.model.InventoryWO;
import isep.web.sakila.webapi.service.FilmService;
import isep.web.sakila.webapi.service.InventoryService;

@RestController
public class InventoryRestController {
	@Autowired
	InventoryService inventoryService;
	@Autowired
	FilmService filmService;
	
	private static final Log log = LogFactory.getLog(InventoryRestController.class);
	
	@RequestMapping(value = "/inventory/", method = RequestMethod.GET)
	public ResponseEntity<List<InventoryWO>> listAllInventories() {
		log.debug("List inventories");
		List<InventoryWO> inventories = inventoryService.findAllInventories();
		log.debug("Got "+inventories.size()+" inventories");
		if (inventories.isEmpty()) {
			return new ResponseEntity<List<InventoryWO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<InventoryWO>>(inventories, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/inventory/store/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<InventoryWO>> listInventoriesByStore(@PathVariable("id") int storeId) {
		log.debug("List inventories by store id : "+storeId);
		List<InventoryWO> inventories = inventoryService.findInventoriesByStore(storeId);
		log.debug("Got "+inventories.size()+" inventories");
		if (inventories.isEmpty()) {
			return new ResponseEntity<List<InventoryWO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<InventoryWO>>(inventories, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/inventory/film/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<InventoryWO>> listInventoriesByFilm(@PathVariable("id") int filmId) {
		log.debug("List inventories by film id : "+filmId);
		List<InventoryWO> inventories = inventoryService.findInventoriesByFilm(filmId);
		log.debug("Got "+inventories.size()+" inventories");
		if (inventories.isEmpty()) {
			return new ResponseEntity<List<InventoryWO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<InventoryWO>>(inventories, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/inventory/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<InventoryWO> getInventory(@PathVariable("id") int id) {
		log.debug("Fetching Inventory with id " + id);
		InventoryWO inventoryWO = inventoryService.findById(id);
		if (inventoryWO == null) {
			System.out.println("Inventory with id " + id + " not found");
			return new ResponseEntity<InventoryWO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<InventoryWO>(inventoryWO, HttpStatus.OK);
	}

	@RequestMapping(value = "/inventory/", method = RequestMethod.POST)
	public ResponseEntity<Void> createInventory(@RequestBody InventoryWO inventoryWO, UriComponentsBuilder ucBuilder) {
		inventoryService.saveInventory(inventoryWO);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/inventory/{id}").buildAndExpand(inventoryWO.getInventoryId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/inventoryUpdate/", method = RequestMethod.POST)
	public ResponseEntity<InventoryWO> updateInventory(@RequestBody InventoryWO inventoryWO, UriComponentsBuilder ucBuilder) {
		log.error(String.format("Updating inventory %s ", inventoryWO.getInventoryId()));
		InventoryWO currentInventory = inventoryService.findById(inventoryWO.getInventoryId());

		if (currentInventory == null) {
			System.out.println("Actor with id " + inventoryWO.getInventoryId() + " not found");
			return new ResponseEntity<InventoryWO>(HttpStatus.NOT_FOUND);
		}

		currentInventory.setInventoryId(inventoryWO.getInventoryId());
		currentInventory.setStore(inventoryWO.getStore());
		currentInventory.setFilm(inventoryWO.getFilm());
		inventoryService.updateInventory(currentInventory);

		return new ResponseEntity<InventoryWO>(currentInventory, HttpStatus.OK);
	}

	@RequestMapping(value = "/inventoryDelete/{id}", method = RequestMethod.GET)
	public ResponseEntity<InventoryWO> deleteInventory(@PathVariable("id") int id) {

		System.out.println("Fetching & Deleting Inventory with id " + id);

		InventoryWO inventoryWO = inventoryService.findById(id);
		if (inventoryWO == null) {
			System.out.println("Unable to delete. Inventory with id " + id + " not found");
			return new ResponseEntity<InventoryWO>(HttpStatus.NOT_FOUND);
		}

		inventoryService.deleteInventoryById(id);
		return new ResponseEntity<InventoryWO>(HttpStatus.NO_CONTENT);
	}
}
