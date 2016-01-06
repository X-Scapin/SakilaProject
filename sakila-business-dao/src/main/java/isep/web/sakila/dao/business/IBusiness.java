package isep.web.sakila.dao.business;

import java.util.List;

import isep.web.sakila.jpa.entities.Actor;

public interface IBusiness {
	public List<Actor> getAllActors();

	public Actor getByID(int actorId);

}