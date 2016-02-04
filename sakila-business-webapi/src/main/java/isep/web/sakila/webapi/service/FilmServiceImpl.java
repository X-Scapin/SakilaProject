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

import isep.web.sakila.dao.repositories.ActorRepository;
import isep.web.sakila.dao.repositories.CategoryRepository;
import isep.web.sakila.dao.repositories.FilmActorRepository;
import isep.web.sakila.dao.repositories.FilmCategoryRepository;
import isep.web.sakila.dao.repositories.FilmRepository;
import isep.web.sakila.dao.repositories.LanguageRepository;
import isep.web.sakila.jpa.entities.Actor;
import isep.web.sakila.jpa.entities.Category;
import isep.web.sakila.jpa.entities.Film;
import isep.web.sakila.jpa.entities.FilmActor;
import isep.web.sakila.jpa.entities.FilmActorPK;
import isep.web.sakila.jpa.entities.FilmCategory;
import isep.web.sakila.jpa.entities.FilmCategoryPK;
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
	private CategoryRepository categoryRepository;

	@Autowired
	private ActorRepository actorRepository;
	
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
		film2update.setReleaseYear(null);
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

	@Override
	public void addCategory(int id, byte categoryId) {
		Film film = filmRepository.findOne(id);
		Category category = categoryRepository.findOne(categoryId);
		if(film!=null && category!=null){
			FilmCategory filmCat = new FilmCategory();
			filmCat.setLastUpdate(new Timestamp(System.currentTimeMillis()));
			FilmCategoryPK fck = new FilmCategoryPK();
			fck.setCategoryId(categoryId);
			fck.setFilmId(id);
			filmCat.setId(fck);
			filmCat.setCategory(category);
			filmCat.setFilm(film);
			film.addFilmCategory(filmCat);
			filmCategoryRepository.save(filmCat);
			filmRepository.save(film);
		}
	}

	@Override
	public void removeCategory(int id, byte categoryId) {
		Film film = filmRepository.findOne(id);
		Category category = categoryRepository.findOne(categoryId);
		if(film!=null && category!=null)
			for(FilmCategory filmCategory : new LinkedList<FilmCategory>(film.getFilmCategories())){
				if(filmCategory.getCategory()==category){
					film.removeFilmCategory(filmCategory);
					filmCategoryRepository.delete(filmCategory);
					filmRepository.save(film);
				}
			}
	}

	@Override
	public void addActor(int id, int actorId) {
		Film film = filmRepository.findOne(id);
		Actor actor = actorRepository.findOne(actorId);
		if(film!=null && actor!=null){
			FilmActor filmAct = new FilmActor();
			filmAct.setLastUpdate(new Timestamp(System.currentTimeMillis()));
			FilmActorPK fpk = new FilmActorPK();
			fpk.setActorId(actorId);
			fpk.setFilmId(id);
			filmAct.setId(fpk);
			filmAct.setActor(actor);
			filmAct.setFilm(film);
			film.addFilmActor(filmAct);
			filmActorRepository.save(filmAct);
			filmRepository.save(film);
		}
	}

	@Override
	public void removeActor(int id, int actorId) {
		Film film = filmRepository.findOne(id);
		Actor actor = actorRepository.findOne(actorId);
		if(film!=null && actor!=null)
			for(FilmActor filmActor : new LinkedList<FilmActor>(film.getFilmActors())){
				if(filmActor.getActor()==actor){
					film.removeFilmActor(filmActor);
					filmActorRepository.delete(filmActor);
					filmRepository.save(film);
				}
			}
	}
}
