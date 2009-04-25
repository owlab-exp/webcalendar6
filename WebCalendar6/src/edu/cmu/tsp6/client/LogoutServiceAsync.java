package edu.cmu.tsp6.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>LogoutService</code>.
 */
public interface LogoutServiceAsync {
	//void loginServer(String id, String password, AsyncCallback<String> callback)throws Exception;
	void logoutServer(String id, AsyncCallback<String> callback) throws Exception;
	void logoutServer(AsyncCallback<String> callback) throws Exception;
}
