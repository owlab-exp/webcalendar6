/**
 * 
 */
package edu.cmu.tsp6.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

import edu.cmu.tsp6.client.bo.User;

/**
 * @author YONG
 *
 */
public interface RegistryServiceAsync {
	/**
	 * Adds an event. The event must have an associated user, a date
	 * @param user 
	 */
	void createUser (User user, AsyncCallback<User> callback); 
	/**
	 * Updates the event information if the user is the owner of the event
	 * @param user
	 * @throws Exception
	 */
	void updateUser(User user, AsyncCallback<User> callback) throws Exception;
	
	/**
	 * Shows the updated event information
	 * @param userID : user ID
	 * @throws Exception
	 */
	void findUser(String userID, AsyncCallback<Void> callback) throws Exception;
	
	
}
