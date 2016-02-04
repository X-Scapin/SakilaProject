package isep.web.sakila.webapi.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import isep.web.sakila.webapi.model.CategoryWO;
import isep.web.sakila.webapi.service.CategoryService;

@RestController
public class CategoryRestController {
	@Autowired
	CategoryService categoryService;
	
	private static final Log log = LogFactory.getLog(ActorRestController.class);

	@RequestMapping(value = "/category/", method = RequestMethod.GET)
	public ResponseEntity<List<CategoryWO>> listAllCategories() {
		List<CategoryWO> categorys = categoryService.findAllCategories();
		if (categorys.isEmpty()) {
			return new ResponseEntity<List<CategoryWO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<CategoryWO>>(categorys, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/category/film/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<CategoryWO>> listAllCategoryieByStore(@PathVariable("id") int filmID) {
		List<CategoryWO> categorys = categoryService.findAllCategoriesFromFilm(filmID);
		if (categorys.isEmpty()) {
			return new ResponseEntity<List<CategoryWO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<CategoryWO>>(categorys, HttpStatus.OK);
	}
}
