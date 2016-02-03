package isep.web.sakila.webapi.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import isep.web.sakila.dao.repositories.AddressRepository;
import isep.web.sakila.dao.repositories.CustomerRepository;
import isep.web.sakila.dao.repositories.StoreRepository;
import isep.web.sakila.jpa.entities.Address;
import isep.web.sakila.jpa.entities.Customer;
import isep.web.sakila.jpa.entities.Inventory;
import isep.web.sakila.jpa.entities.Store;
import isep.web.sakila.webapi.model.CustomerWO;
import isep.web.sakila.webapi.model.InventoryWO;

@Service("customerService")
@Transactional
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private StoreRepository storeRepository;

	private static final Log log = LogFactory.getLog(CustomerServiceImpl.class);

	public List<CustomerWO> findAllCustomers() {
		List<CustomerWO> customers = new LinkedList<CustomerWO>();

		for (Customer customer : customerRepository.findAll()) {
			customers.add(new CustomerWO(customer));
			log.debug("Adding " + customer);
		}

		return customers;
	}

	public List<CustomerWO> findAllCustomersByStore(int storeId) {
		List<CustomerWO> customers = new LinkedList<CustomerWO>();

		for (Customer customer : customerRepository.findAll()) {
			if(customer.getStore().getStoreId()==storeId)
				customers.add(new CustomerWO(customer));
		}

		return customers;
	}

	public CustomerWO findById(int id) {
		log.debug(String.format("Looking for user by Id %s", id));
		Customer customer = customerRepository.findOne(id);

		if (customer != null) {
			return new CustomerWO(customer);
		}
		return null;
	}

	public void saveCustomer(CustomerWO customerWO) {
		Customer customer = new Customer();
		customer.setLastName(customerWO.getLastName());
		customer.setFirstName(customerWO.getFirstName());
		customer.setEmail(customerWO.getEmail());
		
		Address address = addressRepository.findOne(customerWO.getAddress_id());
		if(address != null){
			customer.setAddress(address);
		}
		
		Store store = storeRepository.findOne((byte) customerWO.getStore_id());
		if(store != null){
			customer.setStore(store);
		}
		Date dateobj = new Date();
		customer.setCreateDate(dateobj);
		customer.setLastUpdate(new Timestamp(System.currentTimeMillis()));
		customerRepository.save(customer);
	}

	public void updateCustomer(CustomerWO customerWO) {
		Customer customer2update = customerRepository.findOne(customerWO.getCustomerId());
		customer2update.setLastName(customerWO.getLastName());
		customer2update.setFirstName(customerWO.getFirstName());
		
		Address address = addressRepository.findOne(customerWO.getAddress_id());
		if(address != null){
			customer2update.setAddress(address);
		}
		
		Store store = storeRepository.findOne((byte) customerWO.getStore_id());
		if(store != null){
			customer2update.setStore(store);
		}
		
		customer2update.setLastUpdate(new Timestamp(System.currentTimeMillis()));
		customerRepository.save(customer2update);
	}
	
	public void deleteCustomerById(int id) {
		customerRepository.delete(id);
	}
}
