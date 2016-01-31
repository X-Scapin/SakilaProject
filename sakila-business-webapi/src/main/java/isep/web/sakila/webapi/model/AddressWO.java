package isep.web.sakila.webapi.model;

import isep.web.sakila.jpa.entities.Address;

public class AddressWO extends WebObject{
	private static final long serialVersionUID = -5609060932799540921L;
	protected int addressId;
	protected String address;
	protected String address2;
	protected String district;
	protected String postal_code;
	protected int city_id;
	
	public AddressWO(){
		super();
	}

	public AddressWO(int addressId, String address, String address2, String district, String postal_code, int city_id) {
		super();
		this.addressId = addressId;
		this.address = address;
		this.address2 = address2;
		this.district = district;
		this.postal_code = postal_code;
		this.city_id = city_id;
	}
	
	public AddressWO(final Address address) {
		super();
		this.addressId = address.getAddressId();
		this.address = address.getAddress();
		this.address2 = address.getAddress2();
		this.district = address.getDistrict();
		this.postal_code = address.getPostalCode();
		this.city_id = address.getCity().getCityId();		
	}
	
	@Override
	public String toString() {
		return "Address [id=" + this.addressId + ", address=" + this.address + ", city_id=" + this.city_id + "]";
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getPostal_code() {
		return postal_code;
	}

	public void setPostal_code(String postal_code) {
		this.postal_code = postal_code;
	}

	public int getCity_id() {
		return city_id;
	}

	public void setCity_id(int city_id) {
		this.city_id = city_id;
	}
}
