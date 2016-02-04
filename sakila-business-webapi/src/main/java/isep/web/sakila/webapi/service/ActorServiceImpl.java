package isep.web.sakila.webapi.service;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import isep.web.sakila.dao.repositories.ActorRepository;
import isep.web.sakila.dao.repositories.FilmRepository;
import isep.web.sakila.jpa.entities.Actor;
import isep.web.sakila.jpa.entities.Film;
import isep.web.sakila.jpa.entities.FilmActor;
import isep.web.sakila.webapi.model.ActorWO;

@Service("actorService")
@Transactional
public class ActorServiceImpl implements ActorService {
	@Autowired
	private ActorRepository actorRepository;
	
	@Autowired
	private FilmRepository filmRepository;

	private static final Log log = LogFactory.getLog(ActorServiceImpl.class);

	public List<ActorWO> findAllActors() {
		List<ActorWO> actors = new LinkedList<ActorWO>();

		for (Actor actor : actorRepository.findAll()) {
			actors.add(new ActorWO(actor));
			log.debug("Adding " + actor);
		}

		return actors;
	}

	@Override
	public List<ActorWO> findAllActorsFromFilm(int filmId) {
		List<ActorWO> actors = new LinkedList<ActorWO>();
		Film film = filmRepository.findOne(filmId);
		if(film!=null){
			for(FilmActor filmActor : film.getFilmActors()){
				actors.add(new ActorWO(filmActor.getActor()));
			}
		}else{
			log.debug("Fail to find film with id:" + filmId);
		}
		return actors;
	}

	public ActorWO findById(int id) {
		log.debug(String.format("Looking for user by Id %s", id));
		Actor actor = actorRepository.findOne(id);

		if (actor != null) {
			return new ActorWO(actor);
		}
		return null;
	}

	public void saveActor(ActorWO actorWO) {
		Actor actor = new Actor();
		actor.setLastName(actorWO.getLastName());
		actor.setFirstName(actorWO.getFirstName());
		actor.setLastUpdate(new Timestamp(System.currentTimeMillis()));
		actorRepository.save(actor);
	}

	public void updateActor(ActorWO actorWO) {
		Actor actor2update = actorRepository.findOne(actorWO.getActorId());
		actor2update.setLastName(actorWO.getLastName());
		actor2update.setFirstName(actorWO.getFirstName());
		actor2update.setLastUpdate(new Timestamp(System.currentTimeMillis()));
		actorRepository.save(actor2update);
	}
	
	public void deleteActorById(int id) {
		actorRepository.delete(id);
	}

}
