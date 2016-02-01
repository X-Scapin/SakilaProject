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


import isep.web.sakila.webapi.model.AddressWO;
import isep.web.sakila.webapi.service.AddressService;

@RestController
public class AddressRestController {
	@Autowired
	AddressService addressService;
	
	private static final Log log = LogFactory.getLog(AddressRestController.class);

	@RequestMapping(value = "/address/", method = RequestMethod.GET)
	public ResponseEntity<List<AddressWO>> listAllAddresss() {
		List<AddressWO> addresss = addressService.findAllAddresss();
		if (addresss.isEmpty()) {
			return new ResponseEntity<List<AddressWO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<AddressWO>>(addresss, HttpStatus.OK);
	}

	@RequestMapping(value = "/address/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AddressWO> getAddress(@PathVariable("id") int id) {
		System.out.println("Fetching Address with id " + id);
		AddressWO addressWO = addressService.findById(id);
		if (addressWO == null) {
			System.out.println("Address with id " + id + " not found");
			return new ResponseEntity<AddressWO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<AddressWO>(addressWO, HttpStatus.OK);
	}

	// -------------------Create a Address----------------------------------

	@RequestMapping(value = "/address/", method = RequestMethod.POST)
	public ResponseEntity<Void> createAddress(@RequestBody AddressWO addressWO, UriComponentsBuilder ucBuilder) {
		System.out.println("Creating Address " + addressWO.getAddress());

		addressService.saveAddress(addressWO);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/address/{id}").buildAndExpand(addressWO.getAddressId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/addressUpdate/", method = RequestMethod.POST)
	public ResponseEntity<AddressWO> updateAddress(@RequestBody AddressWO addressWO, UriComponentsBuilder ucBuilder) {
		log.error(String.format("Updating Address %s ", addressWO.getAddressId()));
		AddressWO currentAddress = addressService.findById(addressWO.getAddressId());

		if (currentAddress == null) {
			System.out.println("Address with id " + addressWO.getAddressId() + " not found");
			return new ResponseEntity<AddressWO>(HttpStatus.NOT_FOUND);
		}
		addressService.updateAddress(addressWO);

		return new ResponseEntity<AddressWO>(currentAddress, HttpStatus.OK);
	}

	@RequestMapping(value = "/addressDelete/{id}", method = RequestMethod.GET)
	public ResponseEntity<AddressWO> deleteAddress(@PathVariable("id") int id) {

		System.out.println("Fetching & Deleting Address with id " + id);

		AddressWO staffWO = addressService.findById(id);
		if (staffWO == null) {
			System.out.println("Unable to delete. Address with id " + id + " not found");
			return new ResponseEntity<AddressWO>(HttpStatus.NOT_FOUND);
		}

		addressService.deleteAddressById(id);
		return new ResponseEntity<AddressWO>(HttpStatus.NO_CONTENT);
	}
}
