package edu.cmu.tsp6.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("event_remover")
public interface EventRemoveService extends RemoteService {
	/**
	 * 
	 * @param userid	: event ID
	 * @return			: success or failure with reason message
	 */
	String removeEvent(String eventId, String userId);
}
