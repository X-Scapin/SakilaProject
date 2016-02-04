package isep.web.sakila.webapi.service;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import isep.web.sakila.dao.repositories.CategoryRepository;
import isep.web.sakila.dao.repositories.FilmRepository;
import isep.web.sakila.jpa.entities.Category;
import isep.web.sakila.jpa.entities.Film;
import isep.web.sakila.jpa.entities.FilmCategory;
import isep.web.sakila.webapi.model.CategoryWO;

@Service("categoryService")
@Transactional
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private FilmRepository filmRepository;
	
	private static final Log log = LogFactory.getLog(CategoryServiceImpl.class);

	public List<CategoryWO> findAllCategories() {
		List<CategoryWO> categories = new LinkedList<CategoryWO>();

		for (Category category : categoryRepository.findAll()) {
			categories.add(new CategoryWO(category));
			log.debug("Adding " + category);
		}

		return categories;
	}
	
	public List<CategoryWO> findAllCategoriesFromFilm(int filmId) {
		List<CategoryWO> categories = new LinkedList<CategoryWO>();
		Film film = filmRepository.findOne(filmId);
		if(film!=null){
			for(FilmCategory filmCat : film.getFilmCategories()){
				categories.add(new CategoryWO(filmCat.getCategory()));
			}
		}else{
			log.debug("Fail to find film with id:" + filmId);
		}
		return categories;
	}
}
