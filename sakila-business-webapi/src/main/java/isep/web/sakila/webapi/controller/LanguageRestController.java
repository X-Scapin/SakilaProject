package isep.web.sakila.webapi.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import isep.web.sakila.webapi.model.CustomerWO;
import isep.web.sakila.webapi.model.LanguageWO;
import isep.web.sakila.webapi.service.LanguageService;

@RestController
public class LanguageRestController {
	@Autowired
	LanguageService languageService;
	
	private static final Log log = LogFactory.getLog(ActorRestController.class);

	@RequestMapping(value = "/language/", method = RequestMethod.GET)
	public ResponseEntity<List<LanguageWO>> listAllLanguages() {
		List<LanguageWO> languages = languageService.findAllLanguages();
		if (languages.isEmpty()) {
			return new ResponseEntity<List<LanguageWO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<LanguageWO>>(languages, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/language/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LanguageWO> getLanguage(@PathVariable("id") byte id) {
		System.out.println("Fetching Language with id " + id);
		LanguageWO staffWO = languageService.findById(id);
		if (staffWO == null) {
			System.out.println("Language with id " + id + " not found");
			return new ResponseEntity<LanguageWO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<LanguageWO>(staffWO, HttpStatus.OK);
	}
}
