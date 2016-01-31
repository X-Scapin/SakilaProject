package isep.web.sakila.webapi.model;

import isep.web.sakila.jpa.entities.Customer;

public class CustomerWO extends WebObject{
	private static final long serialVersionUID = -3940626561941477764L;
	
	protected int customerId;
	protected String lastName;
	protected String firstName;
	protected String email;
	protected int address_id;
	protected int store_id;
	
	
	public CustomerWO() {
		super();
	}
	
	public CustomerWO(int customerId, String lastName, String firstName, String email, int address_id, int store_id) {
		super();
		this.customerId = customerId;
		this.lastName = lastName;
		this.firstName = firstName;
		this.email = email;
		this.address_id = address_id;
		this.store_id = store_id;
	}

	public CustomerWO(final Customer customer) {
		super();
		this.customerId = customer.getCustomerId();
		this.lastName = customer.getLastName();
		this.firstName = customer.getFirstName();
		this.email = customer.getEmail();
		this.address_id = customer.getAddress().getAddressId();
		this.store_id = customer.getStore().getStoreId();		
	}
	
	@Override
	public String toString() {
		return "Customer [id=" + this.customerId + ", LastNanem=" + this.lastName + ", First=" + this.firstName + "]";
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAddress_id() {
		return address_id;
	}

	public void setAddress_id(int adressId) {
		this.address_id = adressId;
	}

	public int getStore_id() {
		return store_id;
	}

	public void setStrore_id(int store_id) {
		this.store_id = store_id;
	}
}
