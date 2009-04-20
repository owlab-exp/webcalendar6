package edu.cmu.tsp6.client.bo;



/** 
 * This class represents a new user.  
 * New user or updated user could be considered as new user 
 * to process new or update user as user object.
 * 
 * @author YONG
 *
 */
public class NewUser extends User {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	User newUser;

	public User getNewUser() {
		return newUser;
	}

	public void setNewUser(User newUser) {
		this.newUser = newUser;
	}
	
		
}
