package isep.web.sakila.dao.repositories;

import org.springframework.data.repository.CrudRepository;

import isep.web.sakila.jpa.entities.Language;

public interface LanguageRepository extends CrudRepository<Language, Byte> {

}
