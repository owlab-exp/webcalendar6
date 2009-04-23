package edu.cmu.tsp6.server.serviceImplementations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import edu.cmu.tsp6.client.RemoveEventService;
import edu.cmu.tsp6.client.bo.Event;
import edu.cmu.tsp6.server.dao.EventDAO;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class RemoveEventServiceImpl extends RemoteServiceServlet implements
		RemoveEventService {

	public void removeEventServer(Integer eventId) throws Exception {
		System.out.println("eventId=" + eventId);

		HttpServletRequest request = null;
		HttpSession session = null;
		
		EventDAO ed = EventDAO.getInstance();
		
		//Event event = ed.getEvent(eventId);
		ed.deleteEvent(eventId);
			
	}
}
