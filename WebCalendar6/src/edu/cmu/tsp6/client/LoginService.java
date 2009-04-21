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
	 * @param userid	: 
	 * @param password	: 
	 * @throws Exception: to notify failure situation
	 */
	public void loginServer(String id, String password) throws Exception;
}
