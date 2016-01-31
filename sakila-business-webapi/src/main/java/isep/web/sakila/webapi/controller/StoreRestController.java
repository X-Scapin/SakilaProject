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

import isep.web.sakila.webapi.model.StoreWO;
import isep.web.sakila.webapi.service.StoreService;

@RestController
public class StoreRestController {
	@Autowired
	StoreService storeService;
	
	private static final Log log = LogFactory.getLog(StoreRestController.class);
	
	@RequestMapping(value = "/store/", method = RequestMethod.GET)
	public ResponseEntity<List<StoreWO>> listAllStores() {
		log.debug("List stores");
		List<StoreWO> stores = storeService.findAllStores();
		if (stores.isEmpty()) {
			return new ResponseEntity<List<StoreWO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<StoreWO>>(stores, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/store/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StoreWO> getStore(@PathVariable("id") int id) {
		log.debug("Fetching Store with id " + id);
		StoreWO storeWO = storeService.findById((byte) id);
		if (storeWO == null) {
			System.out.println("Store with id " + id + " not found");
			return new ResponseEntity<StoreWO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<StoreWO>(storeWO, HttpStatus.OK);
	}

	@RequestMapping(value = "/store/", method = RequestMethod.POST)
	public ResponseEntity<Void> createStore(@RequestBody StoreWO storeWO, UriComponentsBuilder ucBuilder) {
		storeService.saveStore(storeWO);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/store/{id}").buildAndExpand(storeWO.getStoreId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/storeUpdate/", method = RequestMethod.POST)
	public ResponseEntity<StoreWO> updateStore(@RequestBody StoreWO storeWO, UriComponentsBuilder ucBuilder) {
		log.error(String.format("Updating store %s ", storeWO.getStoreId()));
		StoreWO currentStore = storeService.findById(storeWO.getStoreId());

		if (currentStore == null) {
			System.out.println("Actor with id " + storeWO.getStoreId() + " not found");
			return new ResponseEntity<StoreWO>(HttpStatus.NOT_FOUND);
		}
		
		storeService.updateStore(storeWO);

		return new ResponseEntity<StoreWO>(currentStore, HttpStatus.OK);
	}

	@RequestMapping(value = "/storeDelete/{id}", method = RequestMethod.GET)
	public ResponseEntity<StoreWO> deleteStore(@PathVariable("id") int id) {

		System.out.println("Fetching & Deleting Store with id " + id);

		StoreWO storeWO = storeService.findById(id);
		if (storeWO == null) {
			System.out.println("Unable to delete. Store with id " + id + " not found");
			return new ResponseEntity<StoreWO>(HttpStatus.NOT_FOUND);
		}

		storeService.deleteStoreById(id);
		return new ResponseEntity<StoreWO>(HttpStatus.NO_CONTENT);
	}
}
