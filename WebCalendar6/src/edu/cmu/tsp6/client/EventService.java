package edu.cmu.tsp6.client;

import com.google.gwt.user.client.rpc.RemoteService;

import edu.cmu.tsp6.businessObjects.Event;
import edu.cmu.tsp6.businessObjects.User;

public interface EventService extends RemoteService {
	/**
	 * Adds an event. The event must have an associated user, a date, and indicate whether it
	 * is a surprise.
	 * @param event 
	 */
	void addEvent(Event event);
	/**
	 * @param eventId	: event ID 
	 * @param userId	: user ID to know if the user is the owner of the event
	 * @throws Exception: to notify if any error
	 */
	void removeEvent(String eventId, String userId) throws Exception;
	
	/**
	 * Gets the user's information by username
	 * @param userName
	 * @return
	 */
	User getUser(String userName);
}
