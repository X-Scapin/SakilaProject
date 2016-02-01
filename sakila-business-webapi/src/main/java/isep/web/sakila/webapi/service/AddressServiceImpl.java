package isep.web.sakila.webapi.service;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import isep.web.sakila.dao.repositories.AddressRepository;
import isep.web.sakila.dao.repositories.CityRepository;
import isep.web.sakila.jpa.entities.Address;
import isep.web.sakila.jpa.entities.City;
import isep.web.sakila.webapi.model.AddressWO;

@Service("addressService")
@Transactional
public class AddressServiceImpl implements AddressService {
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private CityRepository cityRepository;

	private static final Log log = LogFactory.getLog(AddressServiceImpl.class);

	public List<AddressWO> findAllAddresss() {
		List<AddressWO> addresss = new LinkedList<AddressWO>();

		for (Address address : addressRepository.findAll()) {
			addresss.add(new AddressWO(address));
			log.debug("Adding " + address);
		}

		return addresss;
	}

	public AddressWO findById(int id) {
		log.debug(String.format("Looking for user by Id %s", id));
		Address address = addressRepository.findOne(id);

		if (address != null) {
			return new AddressWO(address);
		}
		return null;
	}

	public void saveAddress(AddressWO addressWO) {
		Address address = new Address();
		address.setAddress(addressWO.getAddress());
		address.setAddress2(addressWO.getAddress2());
		address.setDistrict(addressWO.getDistrict());
		address.setPostalCode(addressWO.getPostal_code());
		address.setPhone("");
		
		City city = cityRepository.findOne(addressWO.getCity_id());
		if(city != null){
			address.setCity(city);
		}
		
		address.setLastUpdate(new Timestamp(System.currentTimeMillis()));
		addressRepository.save(address);
	}

	public void updateAddress(AddressWO addressWO) {
		Address address2update = addressRepository.findOne(addressWO.getAddressId());
		address2update.setAddress(addressWO.getAddress());
		address2update.setAddress2(addressWO.getAddress2());
		address2update.setDistrict(addressWO.getDistrict());
		address2update.setPostalCode(addressWO.getPostal_code());
		
		
		City city = cityRepository.findOne(addressWO.getCity_id());
		if(city != null){
			address2update.setCity(city);
		}
		
		address2update.setLastUpdate(new Timestamp(System.currentTimeMillis()));
		addressRepository.save(address2update);
	}
	
	public void deleteAddressById(int id) {
		addressRepository.delete(id);
	}
}
