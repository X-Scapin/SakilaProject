package isep.web.sakila.webapi.service;

import java.util.List;

import isep.web.sakila.webapi.model.LanguageWO;

public interface LanguageService {

	List<LanguageWO> findAllLanguages();

	LanguageWO findById(byte id);
}
