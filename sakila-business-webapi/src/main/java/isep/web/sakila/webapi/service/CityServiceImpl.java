package isep.web.sakila.webapi.service;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import isep.web.sakila.dao.repositories.CityRepository;
import isep.web.sakila.jpa.entities.City;
import isep.web.sakila.webapi.model.CityWO;

@Service("cityService")
@Transactional
public class CityServiceImpl implements CityService {
	@Autowired
	private CityRepository cityRepository;

	private static final Log log = LogFactory.getLog(CityServiceImpl.class);

	public List<CityWO> findAllCities() {
		List<CityWO> citys = new LinkedList<CityWO>();

		for (City city : cityRepository.findAll()) {
			citys.add(new CityWO(city));
			log.debug("Adding " + city);
		}

		return citys;
	}
}
