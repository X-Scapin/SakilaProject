package isep.web.sakila.webapi.service;

import java.util.List;

import isep.web.sakila.jpa.entities.Address;
import isep.web.sakila.webapi.model.AddressWO;

public interface AddressService {
	AddressWO findById(int id);

	Address saveAddress(AddressWO addressWO);

	void updateAddress(AddressWO addressWO);

	void deleteAddressById(int id);

	List<AddressWO> findAllAddresss();

}