package edu.cmu.tsp6.client;

import com.google.gwt.user.client.rpc.RemoteService;
import edu.cmu.tsp6.businessObjects.User;

public interface RegistryService extends RemoteService  {
	 	 
	/**
	 * Insert a user. The user must have required data 	 * 
	 * @param user 
	 */
	public void createUser(User user) throws Exception ; 
	/**
	 * Update a user profile. The user must have required data 	 * 
	 * @param user 
	 */
	public void updateUser(User user) throws Exception ;
	
	/**
	 * find the user then return the user information 
	 * @param userID	: user ID 
	 * @return user		: when it has
	 * @return null		: when it does not have
	 * @throws Exception: to notify if any error
	 */
	public User findUser(String userId) throws Exception ;
	 
}
