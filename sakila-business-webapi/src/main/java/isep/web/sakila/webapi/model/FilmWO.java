package isep.web.sakila.webapi.model;

import java.util.List;
import java.util.Set;

public class FilmWO extends WebObject {
	private static final long serialVersionUID = -8494386839845787279L;
	
	private int filmId;
	private String title;
	private String description;
	private List<Integer> filmActors;
	private List<Integer> filmCategories;
	// Useless when inserting a film ?
//	private List<Integer> inventories;
	private int language1;
	private int language2;
	private int length;
	private int releaseYear;
	private int rentalDuration;
	private float rentalRate;
	private float rentalCost;
	private Set<String> specialFeatures;
	
	public FilmWO() {
		super();
	}
	
	public FilmWO(int filmId, String title, String description, List<Integer> filmActors, List<Integer> filmCategories,
			int language1, int length, int releaseYear, int rentalDuration, float rentalRate, float rentalCost,
			Set<String> specialFeatures) {
		super();
		this.filmId = filmId;
		this.title = title;
		this.description = description;
		this.filmActors = filmActors;
		this.filmCategories = filmCategories;
		this.language1 = language1;
		this.length = length;
		this.releaseYear = releaseYear;
		this.rentalDuration = rentalDuration;
		this.rentalRate = rentalRate;
		this.rentalCost = rentalCost;
		this.specialFeatures = specialFeatures;
	}

	public int getFilmId() {
		return filmId;
	}
	public void setFilmId(int filmId) {
		this.filmId = filmId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<Integer> getFilmActors() {
		return filmActors;
	}
	public void setFilmActors(List<Integer> filmActors) {
		this.filmActors = filmActors;
	}
	public List<Integer> getFilmCategories() {
		return filmCategories;
	}
	public void setFilmCategories(List<Integer> filmCategories) {
		this.filmCategories = filmCategories;
	}
	public int getLanguage1() {
		return language1;
	}
	public void setLanguage1(int language1) {
		this.language1 = language1;
	}
	public int getLanguage2() {
		return language2;
	}
	public void setLanguage2(int language2) {
		this.language2 = language2;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public int getReleaseYear() {
		return releaseYear;
	}
	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}
	public int getRentalDuration() {
		return rentalDuration;
	}
	public void setRentalDuration(int rentalDuration) {
		this.rentalDuration = rentalDuration;
	}
	public float getRentalRate() {
		return rentalRate;
	}
	public void setRentalRate(float rentalRate) {
		this.rentalRate = rentalRate;
	}
	public float getRentalCost() {
		return rentalCost;
	}
	public void setRentalCost(float rentalCost) {
		this.rentalCost = rentalCost;
	}
	public Set<String> getSpecialFeatures() {
		return specialFeatures;
	}
	public void setSpecialFeatures(Set<String> specialFeatures) {
		this.specialFeatures = specialFeatures;
	}
}
