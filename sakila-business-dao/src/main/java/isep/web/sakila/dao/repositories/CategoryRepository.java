package isep.web.sakila.dao.repositories;

import org.springframework.data.repository.CrudRepository;

import isep.web.sakila.jpa.entities.Category;

public interface CategoryRepository extends CrudRepository<Category, Byte> {

}
