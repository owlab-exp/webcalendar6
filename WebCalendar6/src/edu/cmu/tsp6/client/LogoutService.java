/**
 * @author Sean Park
 * Desc: Logout Service
 */
package edu.cmu.tsp6.client;

public interface LogoutService {
	/**
	 * Logout method handling the logout process of a user
	 * @param userID
	 */
	void logoutServer(String userID) throws Exception;
	/**
	 * invalidate a session from the user
	 * @param sessionID
	 */
//	void sessionInvalidation(){
//		
//	}
}
