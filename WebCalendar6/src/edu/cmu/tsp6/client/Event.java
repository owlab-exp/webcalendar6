package edu.cmu.tsp6.client;

/**
 * This class represents an event that can be registered 
 * in the web calendar system.
 * 
 * @author Varokas
 *
 */
public class Event {
	/** The owner of the event */
	private String owner;

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}
}
