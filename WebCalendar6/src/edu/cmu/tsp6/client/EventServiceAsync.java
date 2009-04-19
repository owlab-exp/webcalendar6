/**
 * 
 */
package edu.cmu.tsp6.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

import edu.cmu.tsp6.client.bo.Event;
import edu.cmu.tsp6.client.bo.User;

/**
 * @author kaalpurush
 *
 */
public interface EventServiceAsync {
	/**
	 * Adds an event. The event must have an associated user, a date
	 * @param event 
	 */
	void addEvent(Event event, AsyncCallback<Void> callback);
	
	void getCurrentUser(AsyncCallback<User> callback);
	/**
	 * @param eventId	: event ID 
	 * @param userId	: user ID to know if the user is the owner of the event
	 * @throws Exception: to notify if any error
	 */
	void removeEvent(String eventId, String userId, AsyncCallback<Void> callback) throws Exception;
	
	/**
	 * Gets the user's information by username
	 * @param userName
	 * @return
	 */
	void getUser(String userName, AsyncCallback<User> callback);
	
	/**
	 * Updates the event information if the user is the owner of the event
	 * @param event
	 * @throws Exception
	 */
	void updateEvent(Event event, AsyncCallback<Void> callback) throws Exception;
	
	/**
	 * Shows the updated event information
	 * @param eventID : event ID
	 * @throws Exception
	 */
	void showUpdatedEvent(String eventID, AsyncCallback<Void> callback) throws Exception;
	
	
}
