package edu.cmu.tsp6.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface LoginServiceAsync {
	void greetServer(String input, AsyncCallback<String> callback);
}
