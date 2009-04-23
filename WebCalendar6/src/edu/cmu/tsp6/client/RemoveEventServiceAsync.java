package edu.cmu.tsp6.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>RemoveEventService</code>.
 */
public interface RemoveEventServiceAsync {
	public void removeEventServer(Integer eventId, AsyncCallback<String> callback)throws Exception;
}
