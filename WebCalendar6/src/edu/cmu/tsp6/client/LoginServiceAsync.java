package edu.cmu.tsp6.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>LoginService</code>.
 */
public interface LoginServiceAsync {
	void loginServer(String id, String password, AsyncCallback<String> callback)throws Exception;
}
