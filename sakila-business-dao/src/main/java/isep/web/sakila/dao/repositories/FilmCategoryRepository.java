package isep.web.sakila.dao.repositories;

import org.springframework.data.repository.CrudRepository;

import isep.web.sakila.jpa.entities.FilmCategory;

public interface FilmCategoryRepository extends CrudRepository<FilmCategory, Byte> {

}
