/**
 * 
 */
package edu.cmu.tsp6.server.serviceImplementations;

import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import edu.cmu.tsp6.client.EventService;
import edu.cmu.tsp6.client.bo.BirthdayEvent;
import edu.cmu.tsp6.client.bo.Event;
import edu.cmu.tsp6.client.bo.User;
import edu.cmu.tsp6.server.dao.EventDAO;
import edu.cmu.tsp6.server.dao.UserDAO;

/**
 * @author kaalpurush
 *
 */
@RemoteServiceRelativePath("events")
public class EventServiceImpl extends RemoteServiceServlet implements EventService {

	private static EventServiceImpl instance = new EventServiceImpl();
	
	public static EventService getInstance() {
		return instance;
	}

//	protected EventServiceImpl() {
//		
//	}
	
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
		BirthdayEvent be = (BirthdayEvent)event;
		//validate if all the fields are present
		if (be.getDate() == null) {
			throw new NullPointerException("Event date should not be null");
		}
		if (be.getOwner() == null) {
			throw new NullPointerException("Event ownder should not be null");
		}
		if (be.getBirthdayPerson() == null) {
			throw new NullPointerException("Birthday person should not be null");
		}
		EventDAO ev = EventDAO.getInstance();
		//validate if event with same birthday person
		if (ev.exitsEventByBirthdayPerson(be.getBirthdayPerson())) {
			throw new IllegalStateException("Birthday person already has a birthday");
		}
		// save it using the EventDAO
		
		ev.addEvent((BirthdayEvent)event);
		//test it
	}

	/* (non-Javadoc)
	 * @see edu.cmu.tsp6.client.EventService#getUser(java.lang.String)
	 */
	@Override
	public User getUser(String userName) {
		UserDAO ud = UserDAO.getInstance();
		return ud.getUser(userName);
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

	@Override
	public User getCurrentUser() {
		User u = (User) this.getThreadLocalRequest().getSession().getAttribute(WebCalendarConstants.USER);
		return u;
	}

}
