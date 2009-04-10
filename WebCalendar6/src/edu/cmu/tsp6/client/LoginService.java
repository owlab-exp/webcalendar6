package edu.cmu.tsp6.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("login")
public interface LoginService extends RemoteService {
	/**
	 * 
	 * @param userid	: user ID
	 * @param password	: password
	 * @return			: success or failure with reason message
	 */
	String loginServer(String userid, String password);
}
