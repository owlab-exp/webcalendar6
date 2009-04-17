/**
 * 
 */
package edu.cmu.tsp6.serviceImplementations;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import edu.cmu.tsp6.bo.BirthdayEvent;
import edu.cmu.tsp6.bo.Event;
import edu.cmu.tsp6.bo.User;
import edu.cmu.tsp6.client.EventService;
import edu.cmu.tsp6.server.dao.EventDAO;

/**
 * @author kaalpurush
 *
 */
public class EventServiceImpl extends RemoteServiceServlet implements EventService {

	private static EventServiceImpl instance = new EventServiceImpl();
	
	public static EventService getInstance() {
		return instance;
	}

	protected EventServiceImpl() {
		
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2564269879091290855L;

	/* (non-Javadoc)
	 * @see edu.cmu.tsp6.client.EventService#addEvent(edu.cmu.tsp6.bo.Event)
	 */
	@Override
	public void addEvent(Event event) {
		// check if correct type
		if (!(event instanceof BirthdayEvent)) {
			throw new UnsupportedOperationException("only birthday events are supported");
		}
		//validate if all the fields are present
		if (event.getDate() == null) {
			throw new NullPointerException("Event date should not be null");
		}
		if (event.getOwner() == null) {
			throw new NullPointerException("Event ownder should not be null");
		}
		// save it using the EventDAO
		EventDAO ev = EventDAO.getInstance();
		ev.addEvent((BirthdayEvent)event);
		//test it
	}

	/* (non-Javadoc)
	 * @see edu.cmu.tsp6.client.EventService#getUser(java.lang.String)
	 */
	@Override
	public User getUser(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see edu.cmu.tsp6.client.EventService#removeEvent(java.lang.String, java.lang.String)
	 */
	@Override
	public void removeEvent(String eventId, String userId) throws Exception {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see edu.cmu.tsp6.client.EventService#showUpdatedEvent(java.lang.String)
	 */
	@Override
	public void showUpdatedEvent(String eventID) throws Exception {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see edu.cmu.tsp6.client.EventService#updateEvent(edu.cmu.tsp6.bo.Event)
	 */
	@Override
	public void updateEvent(Event event) throws Exception {
		// TODO Auto-generated method stub

	}

}
