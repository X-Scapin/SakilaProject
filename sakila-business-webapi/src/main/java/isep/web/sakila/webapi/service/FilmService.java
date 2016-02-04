package isep.web.sakila.webapi.service;

import java.util.List;

import isep.web.sakila.webapi.model.FilmWO;

public interface FilmService {
	FilmWO findById(int id);

	void saveFilm(FilmWO filmWO);

	void updateFilm(FilmWO filmWO);

	void deleteFilmById(int id);

	List<FilmWO> findAllFilms();
	
	void addCategory(int id, byte categoryId);
	void removeCategory(int id, byte categoryId);
	
	void addActor(int id, int actorId);
	void removeActor(int id, int actorId);

}