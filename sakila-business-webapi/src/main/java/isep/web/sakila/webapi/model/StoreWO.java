package isep.web.sakila.webapi.model;

import java.util.LinkedList;
import java.util.List;

import isep.web.sakila.jpa.entities.Customer;
import isep.web.sakila.jpa.entities.Inventory;
import isep.web.sakila.jpa.entities.Staff;
import isep.web.sakila.jpa.entities.Store;

public class StoreWO extends WebObject {
	private static final long serialVersionUID = -3487967980679961161L;

	private int storeId;
	private int adress;
	private List<Integer> customers;
	private List<Integer> inventories;
	private int managerStaff;
	private List<Integer> staffs;
	
	public StoreWO() {
		super();
	}
	
	public StoreWO(Store store) {
		super();
		this.storeId = store.getStoreId();
		if (store.getAddress() != null) {
			this.adress = store.getAddress().getAddressId();
		}
		if (store.getCustomers() != null) {
			List<Integer> customersId = new LinkedList<Integer>();
			for (Customer customer : store.getCustomers()) {
				customersId.add(customer.getCustomerId());
			}
			this.customers = customersId;
		}
		if (store.getInventories() != null) {
			List<Integer> inventoriesId = new LinkedList<Integer>();
			for (Inventory inventory : store.getInventories()) {
				inventoriesId.add(inventory.getInventoryId());
			}
			this.inventories = inventoriesId;
		}
		if (store.getStaff() != null) {
			this.managerStaff = store.getStaff().getStaffId();
		}
		if (store.getStaffs() != null) {
			List<Integer> staffsId = new LinkedList<Integer>();
			for (Staff staff : store.getStaffs()) {
				staffsId.add((int) staff.getStaffId());
			}
			this.staffs = staffsId;
		}
	}
	
	public StoreWO(int storeId, int adress, List<Integer> customers, List<Integer> inventories, int managerStaff,
			List<Integer> staffs) {
		super();
		this.storeId = storeId;
		this.adress = adress;
		this.customers = customers;
		this.inventories = inventories;
		this.managerStaff = managerStaff;
		this.staffs = staffs;
	}

	public int getStoreId() {
		return storeId;
	}
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
	public int getAdress() {
		return adress;
	}
	public void setAdress(int adress) {
		this.adress = adress;
	}
	public List<Integer> getCustomers() {
		return customers;
	}
	public void setCustomers(List<Integer> customers) {
		this.customers = customers;
	}
	public List<Integer> getInventories() {
		return inventories;
	}
	public void setInventories(List<Integer> inventories) {
		this.inventories = inventories;
	}
	public int getManagerStaff() {
		return managerStaff;
	}
	public void setManagerStaff(int managerStaff) {
		this.managerStaff = managerStaff;
	}
	public List<Integer> getStaffs() {
		return staffs;
	}
	public void setStaffs(List<Integer> staffs) {
		this.staffs = staffs;
	}
	
}
