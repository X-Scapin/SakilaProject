package isep.web.sakila.webapi.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import isep.web.sakila.webapi.model.ActorWO;
import isep.web.sakila.webapi.service.ActorService;

@RestController
public class ActorRestController {
	@Autowired
	ActorService actorService;
	
	private static final Log log = LogFactory.getLog(ActorRestController.class);

	@RequestMapping(value = "/actor/", method = RequestMethod.GET)
	public ResponseEntity<List<ActorWO>> listAllActors() {
		List<ActorWO> actors = actorService.findAllActors();
		if (actors.isEmpty()) {
			return new ResponseEntity<List<ActorWO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<ActorWO>>(actors, HttpStatus.OK);
	}

	@RequestMapping(value = "/actor/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ActorWO> getActor(@PathVariable("id") int id) {
		System.out.println("Fetching Actor with id " + id);
		ActorWO staffWO = actorService.findById(id);
		if (staffWO == null) {
			System.out.println("Actor with id " + id + " not found");
			return new ResponseEntity<ActorWO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<ActorWO>(staffWO, HttpStatus.OK);
	}

	// -------------------Create a Actor----------------------------------

	@RequestMapping(value = "/actor/", method = RequestMethod.POST)
	public ResponseEntity<Void> createActor(@RequestBody ActorWO actorWO, UriComponentsBuilder ucBuilder) {
		System.out.println("Creating Actor " + actorWO.getLastName());

		actorService.saveActor(actorWO);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/actor/{id}").buildAndExpand(actorWO.getActorId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/actorUpdate/", method = RequestMethod.POST)
	public ResponseEntity<ActorWO> updateActor(@RequestBody ActorWO actorWO, UriComponentsBuilder ucBuilder) {
		log.error(String.format("Updating Actor %s ", actorWO.getActorId()));
		ActorWO currentActor = actorService.findById(actorWO.getActorId());

		if (currentActor == null) {
			System.out.println("Actor with id " + actorWO.getActorId() + " not found");
			return new ResponseEntity<ActorWO>(HttpStatus.NOT_FOUND);
		}

		currentActor.setLastName(actorWO.getLastName());
		currentActor.setFirstName(actorWO.getFirstName());
		actorService.updateActor(currentActor);

		return new ResponseEntity<ActorWO>(currentActor, HttpStatus.OK);
	}

	@RequestMapping(value = "/actorDelete/{id}", method = RequestMethod.GET)
	public ResponseEntity<ActorWO> deleteActor(@PathVariable("id") int id) {

		System.out.println("Fetching & Deleting Actor with id " + id);

		ActorWO staffWO = actorService.findById(id);
		if (staffWO == null) {
			System.out.println("Unable to delete. Actor with id " + id + " not found");
			return new ResponseEntity<ActorWO>(HttpStatus.NOT_FOUND);
		}

		actorService.deleteActorById(id);
		return new ResponseEntity<ActorWO>(HttpStatus.NO_CONTENT);
	}
}
