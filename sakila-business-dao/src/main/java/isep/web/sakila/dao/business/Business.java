package isep.web.sakila.dao.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import isep.web.sakila.dao.repositories.ActorRepository;
import isep.web.sakila.jpa.entities.Actor;

@Service("business")
public class Business implements IBusiness {
	@Autowired
	private ActorRepository actorRepository;

	public List<Actor> getAllActors() {
		return Lists.newArrayList(actorRepository.findAll());
	}

	public Actor getByID(int actorId) {
		return actorRepository.findOne(actorId);
	}
}
