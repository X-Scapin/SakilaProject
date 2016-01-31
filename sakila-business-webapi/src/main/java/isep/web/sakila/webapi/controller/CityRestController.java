package isep.web.sakila.webapi.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import isep.web.sakila.webapi.model.CityWO;
import isep.web.sakila.webapi.service.CityService;

@RestController
public class CityRestController {
	@Autowired
	CityService cityService;
	
	private static final Log log = LogFactory.getLog(ActorRestController.class);

	@RequestMapping(value = "/city/", method = RequestMethod.GET)
	public ResponseEntity<List<CityWO>> listAllCities() {
		List<CityWO> citys = cityService.findAllCities();
		if (citys.isEmpty()) {
			return new ResponseEntity<List<CityWO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<CityWO>>(citys, HttpStatus.OK);
	}
}
