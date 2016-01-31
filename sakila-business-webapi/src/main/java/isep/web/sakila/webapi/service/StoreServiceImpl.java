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
import isep.web.sakila.dao.repositories.CustomerRepository;
import isep.web.sakila.dao.repositories.InventoryRepository;
import isep.web.sakila.dao.repositories.StaffRepository;
import isep.web.sakila.dao.repositories.StoreRepository;
import isep.web.sakila.jpa.entities.Address;
import isep.web.sakila.jpa.entities.Customer;
import isep.web.sakila.jpa.entities.Inventory;
import isep.web.sakila.jpa.entities.Staff;
import isep.web.sakila.jpa.entities.Store;
import isep.web.sakila.webapi.model.StoreWO;
import isep.web.sakila.webapi.utils.Util;

@Service("storeService")
@Transactional
public class StoreServiceImpl implements StoreService {
	@Autowired
	private StoreRepository storeRepository;
	@Autowired
	private AddressRepository adressRepository;
	@Autowired
	private StaffRepository staffRepository;
	@Autowired
	private InventoryRepository inventoryRepository;
	@Autowired
	private CustomerRepository customerRepository;
	
	private static final Log log = LogFactory.getLog(StoreServiceImpl.class);

	public StoreWO findById(int id) {
		log.debug("Find store with id : "+id);
		Store store = storeRepository.findOne((byte) id);
		
		if (store != null) {
			return new StoreWO(store);
		}
		
		return null;
	}

	public void saveStore(StoreWO storeWO) {
		Store store = new Store();
		Address adress = adressRepository.findOne(storeWO.getAdress());
		Iterable<Customer> customers = customerRepository.findAll(storeWO.getCustomers());
		Iterable<Inventory> inventories = inventoryRepository.findAll(storeWO.getInventories());
		Staff staff = staffRepository.findOne(storeWO.getManagerStaff());
		Iterable<Staff> staffs = staffRepository.findAll(storeWO.getStaffs());
		
		store.setAddress(adress);
		store.setCustomers(Util.toList(customers));
		store.setInventories(Util.toList(inventories));
		store.setStaff(staff);
		store.setStaffs(Util.toList(staffs));
		store.setLastUpdate(new Timestamp(System.currentTimeMillis()));
		storeRepository.save(store);
	}

	public void updateStore(StoreWO storeWO) {
		Store store = storeRepository.findOne((byte) storeWO.getStoreId());
		Address adress = adressRepository.findOne(storeWO.getAdress());
		Iterable<Customer> customers = customerRepository.findAll(storeWO.getCustomers());
		Iterable<Inventory> inventories = inventoryRepository.findAll(storeWO.getInventories());
		Staff staff = staffRepository.findOne(storeWO.getManagerStaff());
		Iterable<Staff> staffs = staffRepository.findAll(storeWO.getStaffs());
		
		store.setAddress(adress);
		store.setCustomers(Util.toList(customers));
		store.setInventories(Util.toList(inventories));
		store.setStaff(staff);
		store.setStaffs(Util.toList(staffs));
		store.setLastUpdate(new Timestamp(System.currentTimeMillis()));
		storeRepository.save(store);
	}

	public void deleteStoreById(int id) {
		storeRepository.delete((byte) id);
	}

	public List<StoreWO> findAllStores() {
		List<StoreWO> stores = new LinkedList<StoreWO>();
		for (Store store : storeRepository.findAll()) {
			stores.add(new StoreWO(store));
			log.debug("add store with id : "+store.getStoreId());
		}
		return stores;
	}

}
