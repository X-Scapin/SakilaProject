package isep.web.sakila;

import java.math.BigDecimal;
import java.util.LinkedList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import isep.web.sakila.webapi.SakilaBusinessWebapiApplication;
import isep.web.sakila.webapi.model.ActorWO;
import isep.web.sakila.webapi.model.CustomerWO;
import isep.web.sakila.webapi.model.FilmWO;
import isep.web.sakila.webapi.service.ActorService;
import isep.web.sakila.webapi.service.CustomerService;
import isep.web.sakila.webapi.service.FilmService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SakilaBusinessWebapiApplication.class)
public class ServicesUnitTest {

	@Autowired
	private ActorService actorService;
	@Autowired
	private CustomerService customerService;

	@Autowired
	private FilmService filmService;

	@Test
	public void actorServiceUnitTest() {
		ActorWO actorWO = new ActorWO(0, "testFirstName", "testLastName");
		actorService.saveActor(actorWO);
		boolean finded = false;
		for (ActorWO curActor : actorService.findAllActors()) {
			if (curActor.getFirstName().equals(actorWO.getFirstName())) {
				actorWO = curActor;
				finded = true;
			}
		}
		assert (finded);
		
		actorService.deleteActorById(actorWO.getActorId());
		assert(actorService.findById(actorWO.getActorId())==null);
	}
	
	@Test
	public void customerServiceUnitTest(){
		CustomerWO customerWO = new CustomerWO(0, "testFirst", "testLast", "test@email.com", 1, 1);
		customerService.saveCustomer(customerWO);
		boolean finded = false;
		for(CustomerWO curCustomer : customerService.findAllCustomers()){
			if(curCustomer.getFirstName().equals(customerWO.getFirstName())){
				customerWO = curCustomer;
				finded=true;
			}
		}
		assert (finded);
		
		customerService.deleteCustomerById(customerWO.getCustomerId());
		assert(customerService.findById(customerWO.getCustomerId())==null);
	}
	
	@Test 
	public void filmServiceUnitTest(){
		FilmWO filmWO = new FilmWO(0, "unitTestFilm", "A testFilm",  new LinkedList<Integer>(), new LinkedList<Byte>(), (byte)1, (byte)1, new BigDecimal(1));
		filmService.saveFilm(filmWO);
		boolean finded = false;
		for(FilmWO curFilm : filmService.findAllFilms()){
			if(curFilm.getTitle().equals(filmWO.getTitle())){
				filmWO = curFilm;
				finded=true;
			}
		}
		assert (finded);
		
		filmService.deleteFilmById(filmWO.getFilmId());
		assert(filmService.findById(filmWO.getFilmId())==null);
	}
	

}
