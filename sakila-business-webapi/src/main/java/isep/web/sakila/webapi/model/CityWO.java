package isep.web.sakila.webapi.model;

import isep.web.sakila.jpa.entities.City;

public class CityWO extends WebObject{
	private static final long serialVersionUID = 3138648623906871304L;
	protected int cityId;
	protected String city;
	protected int country_id;
	
	public CityWO() {
		super();
	}

	public CityWO(final City city) {
		super();
		this.cityId = city.getCityId();
		this.city = city.getCity();
		this.country_id = city.getCountry().getCountryId();
	}
	
	public CityWO(int cityId, String city, int country_id) {
		super();
		this.cityId = cityId;
		this.city = city;
		this.country_id = country_id;
	}
	
	@Override
	public String toString() {
		return "City [id=" + this.cityId + ", city=" + this.city +"]";
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getCountry_id() {
		return country_id;
	}

	public void setCountry_id(int country_id) {
		this.country_id = country_id;
	}
}
