package edu.cmu.tsp6.client;

import com.google.gwt.user.client.rpc.RemoteService;

import edu.cmu.tsp6.bo.Event;
import edu.cmu.tsp6.bo.User;

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
	
	/**
	 * Updates the event information if the user is the owner of the event
	 * @param event
	 * @throws Exception
	 */
	void updateEvent(Event event) throws Exception;
	
	/**
	 * Shows the updated event information
	 * @param eventID : event ID
	 * @throws Exception
	 */
	void showUpdatedEvent(String eventID) throws Exception;
	
	
	
	
	
	
}
