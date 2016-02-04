package isep.web.sakila.webapi.service;

import java.util.List;

import isep.web.sakila.webapi.model.CategoryWO;

public interface CategoryService {

	List<CategoryWO> findAllCategories();
	
	public List<CategoryWO> findAllCategoriesFromFilm(int filmId);

}
