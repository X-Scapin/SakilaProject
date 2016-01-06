package isep.web.sakila.webapi.service;

import java.util.List;

import isep.web.sakila.webapi.model.ActorWO;

public interface ActorService {
	ActorWO findById(int id);

	void saveActor(ActorWO userWO);

	void updateActor(ActorWO userWO);

	void deleteActorById(int id);

	List<ActorWO> findAllActors();

}
