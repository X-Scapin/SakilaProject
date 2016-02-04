package isep.web.sakila.webapi.service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import isep.web.sakila.dao.repositories.FilmActorRepository;
import isep.web.sakila.dao.repositories.FilmCategoryRepository;
import isep.web.sakila.dao.repositories.FilmRepository;
import isep.web.sakila.dao.repositories.LanguageRepository;
import isep.web.sakila.jpa.entities.Film;
import isep.web.sakila.jpa.entities.FilmActor;
import isep.web.sakila.jpa.entities.FilmCategory;
import isep.web.sakila.jpa.entities.Inventory;
import isep.web.sakila.jpa.entities.Language;
import isep.web.sakila.webapi.model.FilmWO;

@Service("filmService")
@Transactional
public class FilmServiceImpl implements FilmService {
	@Autowired
	private FilmRepository filmRepository;
	
	@Autowired
	private LanguageRepository languageRepository;
	
	@Autowired
	private FilmActorRepository filmActorRepository;
	
	@Autowired
	private FilmCategoryRepository filmCategoryRepository;
	
	@Autowired
	private InventoryService inventoryService;
	
	private static final Log log = LogFactory.getLog(FilmServiceImpl.class);

	public List<FilmWO> findAllFilms() {
		List<FilmWO> films = new LinkedList<FilmWO>();

		for (Film film : filmRepository.findAll()) {
			films.add(new FilmWO(film));
			log.debug("Adding " + film);
		}

		return films;
	}

	public FilmWO findById(int id) {
		log.debug(String.format("Looking for user by Id %s", id));
		Film film = filmRepository.findOne(id);

		if (film != null) {
			return new FilmWO(film);
		}
		return null;
	}

	public void saveFilm(FilmWO filmWO) {
		Film film = new Film();
		
		film.setTitle(filmWO.getTitle());
		film.setDescription(filmWO.getDescription());
		film.setRentalDuration(filmWO.getRentalDuration());
		film.setReplacementCost(filmWO.getReplaCost());
		film.setLastUpdate(new Timestamp(System.currentTimeMillis()));
		film.setRentalRate(new BigDecimal(1));
		
		Language language = languageRepository.findOne(filmWO.getLanguage_id());
		if(language !=null){
			film.setLanguage1(language);
		}
		
		filmRepository.save(film);
	}

	public void updateFilm(FilmWO filmWO) {
		Film film2update = filmRepository.findOne(filmWO.getFilmId());
		
		film2update.setTitle(filmWO.getTitle());
		film2update.setDescription(filmWO.getDescription());
		film2update.setRentalDuration(filmWO.getRentalDuration());
		film2update.setReplacementCost(filmWO.getReplaCost());

		
		film2update.setLastUpdate(new Timestamp(System.currentTimeMillis()));
		filmRepository.save(film2update);
	}
	
	public void deleteFilmById(int id) {
		Film film = filmRepository.findOne(id);
		Iterable<FilmActor> actors = film.getFilmActors();
		Iterable<FilmCategory> categories = film.getFilmCategories();
		List<Inventory> inventories = film.getInventories();
		filmActorRepository.delete(actors);
		filmCategoryRepository.delete(categories);
		for (Inventory i : inventories) {
			inventoryService.deleteInventoryById(i.getInventoryId());
		}
		filmRepository.delete(id);
	}
}
