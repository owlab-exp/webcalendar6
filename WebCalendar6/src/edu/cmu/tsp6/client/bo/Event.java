package edu.cmu.tsp6.client.bo;

import java.io.Serializable;
import java.util.Date;
//
public class Event implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5991203450262094509L;
	User owner;
	Date date;
	int eventId;

	public int getEventId() {
		return eventId;
	}
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
}
