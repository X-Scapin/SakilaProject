package isep.web.sakila.webapi.model;

import isep.web.sakila.jpa.entities.Actor;

public class ActorWO extends WebObject {
	private static final long serialVersionUID = -1377067679473844279L;

	protected int actorId;
	protected String lastName;
	protected String firstName;

	public ActorWO() {
		super();
	}

	public ActorWO(int actorId, String lastName, String firstName) {
		super();
		this.actorId = actorId;
		this.lastName = lastName;
		this.firstName = firstName;
	}

	public ActorWO(final Actor actor) {
		super();
		this.actorId = actor.getActorId();
		this.lastName = actor.getLastName();
		this.firstName = actor.getFirstName();
	}

	public String getFirstName() {
		return firstName;
	}

	public int getActorId() {
		return actorId;
	}

	public String getLastName() {
		return lastName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setActorId(int actorId) {
		this.actorId = actorId;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "Actor [id=" + this.actorId + ", LastNanem=" + this.lastName + ", First=" + this.firstName + "]";
	}
}
