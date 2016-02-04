package isep.web.sakila.webapi.service;

import java.util.List;

import isep.web.sakila.webapi.model.ActorWO;
import isep.web.sakila.webapi.model.CategoryWO;

public interface ActorService {
	ActorWO findById(int id);
	
	public List<ActorWO> findAllActorsFromFilm(int filmId);

	void saveActor(ActorWO userWO);

	void updateActor(ActorWO userWO);

	void deleteActorById(int id);

	List<ActorWO> findAllActors();

}
