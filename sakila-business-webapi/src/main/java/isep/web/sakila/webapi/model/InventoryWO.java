package isep.web.sakila.webapi.model;

import isep.web.sakila.jpa.entities.Inventory;

public class InventoryWO extends WebObject {
	private static final long serialVersionUID = 8022142672944572088L;
	
	private int inventoryId;
	private int film;
	private int store;
	
	public InventoryWO() {
		super();
	}
	
	public InventoryWO(Inventory inventory) {
		super();
		this.inventoryId = inventory.getInventoryId();
		if (inventory.getFilm() != null){
			this.film = inventory.getFilm().getFilmId();
		}	
		if (inventory.getStore() != null) {
			this.store = inventory.getStore().getStoreId();
		}
	}
	
	public int getInventoryId() {
		return inventoryId;
	}
	public void setInventoryId(int inventoryId) {
		this.inventoryId = inventoryId;
	}
	public int getFilm() {
		return film;
	}
	public void setFilm(int film) {
		this.film = film;
	}
	public int getStore() {
		return store;
	}
	public void setStore(int store) {
		this.store = store;
	}
	
	@Override
	public String toString() {
		return "Inventory : [id="+inventoryId+", film="+film+", store="+store;
	}
}
