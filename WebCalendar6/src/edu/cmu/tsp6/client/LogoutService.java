/**
 * @author Sean Park
 * Desc: Logout Service
 */
package edu.cmu.tsp6.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("logout")
public interface LogoutService extends RemoteService{
	/**
	 * Logout method handling the logout process of a user
	 * @param userID
	 */
	void logoutServer(String userID) throws Exception;
	void logoutServer() throws Exception;
	/**
	 * invalidate a session from the user
	 * @param sessionID
	 */
//	void sessionInvalidation(){
//		
//	}
}
