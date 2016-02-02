package isep.web.sakila.webapi.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import isep.web.sakila.jpa.entities.Film;
import isep.web.sakila.jpa.entities.FilmActor;
import isep.web.sakila.jpa.entities.FilmCategory;

public class FilmWO extends WebObject {
	private static final long serialVersionUID = -8494386839845787279L;
	
	private int filmId;
	private String title;
	private String description;
	private List<Integer> filmActors;
	private List<Byte> filmCategories;
	private byte language_id;
	private byte rentalDuration;
	private BigDecimal replaCost;
	
	public FilmWO() {
		super();
	}

	public FilmWO(int filmId, String title, String description, List<Integer> filmActors, List<Byte> filmCategories,
			byte language_id, byte rentalDuration, BigDecimal replaCost) {
		super();
		this.filmId = filmId;
		this.title = title;
		this.description = description;
		this.filmActors = filmActors;
		this.filmCategories = filmCategories;
		this.language_id = language_id;
		this.rentalDuration = rentalDuration;
		this.replaCost = replaCost;
	}
	
	public FilmWO(final Film film) {
		super();
		this.filmId = film.getFilmId();
		this.title = film.getTitle();
		this.description = film.getDescription();
		
		this.filmActors = new LinkedList<>();
		for(FilmActor filmActor : film.getFilmActors()){
			filmActors.add(filmActor.getActor().getActorId());
		}
		
		this.filmCategories = new LinkedList<>();
		for(FilmCategory filmCat : film.getFilmCategories()){
			filmCategories.add(filmCat.getCategory().getCategoryId());
		}
		
		this.language_id = film.getLanguage1().getLanguageId();
		this.rentalDuration = film.getRentalDuration();
		this.replaCost = film.getReplacementCost();
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
	public List<Byte> getFilmCategories() {
		return filmCategories;
	}
	public void setFilmCategories(List<Byte> filmCategories) {
		this.filmCategories = filmCategories;
	}
	public byte getLanguage_id() {
		return language_id;
	}
	public void setLanguage_id(byte language_id) {
		this.language_id = language_id;
	}
	public byte getRentalDuration() {
		return rentalDuration;
	}
	public void setRentalDuration(byte rentalDuration) {
		this.rentalDuration = rentalDuration;
	}
	public BigDecimal getReplaCost() {
		return replaCost;
	}
	public void setReplaCost(BigDecimal rentalCost) {
		this.replaCost = rentalCost;
	}
}
