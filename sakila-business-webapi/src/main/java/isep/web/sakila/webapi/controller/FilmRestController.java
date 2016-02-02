package isep.web.sakila.webapi.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;


import isep.web.sakila.webapi.model.FilmWO;
import isep.web.sakila.webapi.service.FilmService;

@RestController
public class FilmRestController {
	@Autowired
	FilmService filmService;
	
	private static final Log log = LogFactory.getLog(FilmRestController.class);

	@RequestMapping(value = "/film/", method = RequestMethod.GET)
	public ResponseEntity<List<FilmWO>> listAllFilms() {
		List<FilmWO> films = filmService.findAllFilms();
		if (films.isEmpty()) {
			return new ResponseEntity<List<FilmWO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<FilmWO>>(films, HttpStatus.OK);
	}

	@RequestMapping(value = "/film/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<FilmWO> getFilm(@PathVariable("id") int id) {
		System.out.println("Fetching Film with id " + id);
		FilmWO filmWO = filmService.findById(id);
		if (filmWO == null) {
			System.out.println("Film with id " + id + " not found");
			return new ResponseEntity<FilmWO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<FilmWO>(filmWO, HttpStatus.OK);
	}

	// -------------------Create a Film----------------------------------

	@RequestMapping(value = "/film/", method = RequestMethod.POST)
	public ResponseEntity<Void> createFilm(@RequestBody FilmWO filmWO, UriComponentsBuilder ucBuilder) {
		System.out.println("Creating Film " + filmWO.getTitle());

		filmService.saveFilm(filmWO);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/film/{id}").buildAndExpand(filmWO.getFilmId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/filmUpdate/", method = RequestMethod.POST)
	public ResponseEntity<FilmWO> updateFilm(@RequestBody FilmWO filmWO, UriComponentsBuilder ucBuilder) {
		log.error(String.format("Updating Film %s ", filmWO.getFilmId()));
		FilmWO currentFilm = filmService.findById(filmWO.getFilmId());

		if (currentFilm == null) {
			System.out.println("Film with id " + filmWO.getFilmId() + " not found");
			return new ResponseEntity<FilmWO>(HttpStatus.NOT_FOUND);
		}
		filmService.updateFilm(filmWO);

		return new ResponseEntity<FilmWO>(currentFilm, HttpStatus.OK);
	}

	@RequestMapping(value = "/filmDelete/{id}", method = RequestMethod.GET)
	public ResponseEntity<FilmWO> deleteFilm(@PathVariable("id") int id) {

		System.out.println("Fetching & Deleting Film with id " + id);

		FilmWO staffWO = filmService.findById(id);
		if (staffWO == null) {
			System.out.println("Unable to delete. Film with id " + id + " not found");
			return new ResponseEntity<FilmWO>(HttpStatus.NOT_FOUND);
		}

		filmService.deleteFilmById(id);
		return new ResponseEntity<FilmWO>(HttpStatus.NO_CONTENT);
	}
}
