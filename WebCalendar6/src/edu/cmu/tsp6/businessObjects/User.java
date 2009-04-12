package edu.cmu.tsp6.businessObjects;
/**
 * This class represent user information
 * in the web calendar system.
 * 
 * @author YONG
 *
 */
public class User {
	private String userId = null;
	private String password = null;
	private String name = null; // -- Full name
	private String email = null;
	
	// -- days prior to send notification
	
	
	public String getEmail() {
		/**
		 * retrieve e-mail data for user
		 */
		return email;
	}
	public String getName() {
		/**
		 * retrieve name data for user
		 */
		return name;
	}
	public String getPassword() {
		/**
		 * retrieve password data for user
		 */
		return password;
	}
	public String getUserId() {
		/**
		 * retrieve user id data for user
		 */
		return userId;
	}
	public void setEmail(String string) {
		/**
		 * set email data for user
		 */
		email = string;
	}
	public void setName(String string) {
		/**
		 * set name data for user
		 */
		name = string;
	}
	public void setPassword(String string) {
		/**
		 * set password data for user
		 */
		password = string;
	}
	public void setUserId(String string) {
		/**
		 * set user id data for user
		 */
		userId = string;
	} 
	
	/**	 
	 * Operation to check match the password for requested Id.	
	 */
	public boolean isMatchPassword(String inputPassword) {
		if (getPassword().equals(inputPassword)) {
			return true;
		} else {
			return false;
		}
/* This is the better.
                return getPassword().equals(inputPassword);
*/
	}
}
