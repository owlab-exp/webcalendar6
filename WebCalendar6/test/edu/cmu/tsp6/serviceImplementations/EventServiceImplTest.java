/**
 * 
 */
package edu.cmu.tsp6.serviceImplementations;

import static org.junit.Assert.fail;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.dbunit.DatabaseUnitException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.ext.mysql.MySqlConnection;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.cmu.tsp6.bo.BirthdayEvent;
import edu.cmu.tsp6.bo.User;
import edu.cmu.tsp6.client.EventService;
import edu.cmu.tsp6.server.dao.DatabaseConnection;
import edu.cmu.tsp6.server.dao.EventDAO;
import edu.cmu.tsp6.server.dao.exception.EventNotExistException;
import static org.junit.Assert.*;

/**
 * @author kaalpurush
 * 
 */
public class EventServiceImplTest {
	Connection connection = DatabaseConnection.getInstance().getConnection();

	/**
	 * Test method for
	 * {@link edu.cmu.tsp6.serviceImplementations.EventServiceImpl#addEvent(edu.cmu.tsp6.bo.Event)}
	 * .
	 */
	@Test
	public void testAddEvent() {
		EventDAO ed = EventDAO.getInstance();
		int eventId = 0;
		try {

			connection.setAutoCommit(false);

			BirthdayEvent birthdayEvent = new BirthdayEvent();

			User owner = new User();
			User birthdayPerson = new User();

			owner.setUserId("coconut");
			birthdayPerson.setUserId("abin");

			birthdayEvent.setOwner(owner);
			birthdayEvent.setBirthdayPerson(birthdayPerson);
			birthdayEvent.setDate(new SimpleDateFormat("yyyy-MM-dd")
					.parse("1984-01-25"));
			// add an event
			EventService es = EventServiceImpl.getInstance();
			es.addEvent(birthdayEvent);
			// query it using the getEvent

			BirthdayEvent be = ed.getEventByOwner(birthdayEvent.getOwner()
					.getUserId());
			eventId = be.getEventId();
			assertTrue(be.getBirthdayPerson().equals(
					birthdayEvent.getBirthdayPerson()));
			assertTrue(be.getOwner().equals(birthdayEvent.getOwner()));
			assertTrue(be.getDate().equals(birthdayEvent.getDate()));

			connection.rollback();
		} catch (Throwable e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			ed.deleteEvent(eventId);
		}
	}

	/**
	 * Test method for
	 * {@link edu.cmu.tsp6.serviceImplementations.EventServiceImpl#addEvent(edu.cmu.tsp6.bo.Event)}
	 * .
	 */
	@Test(expected=IllegalStateException.class)
	public void testAddEventDuplicate() {
		EventDAO ed = EventDAO.getInstance();
		int eventId = 0;
		try {

			connection.setAutoCommit(false);

			BirthdayEvent birthdayEvent = new BirthdayEvent();

			User owner = new User();
			User birthdayPerson = new User();

			owner.setUserId("coconut");
			birthdayPerson.setUserId("abin");

			birthdayEvent.setOwner(owner);
			birthdayEvent.setBirthdayPerson(birthdayPerson);
			birthdayEvent.setDate(new SimpleDateFormat("yyyy-MM-dd")
					.parse("1984-01-25"));
			// add an event
			EventService es = EventServiceImpl.getInstance();
			es.addEvent(birthdayEvent);
			// query it using the getEvent

			BirthdayEvent be = ed.getEventByOwner(birthdayEvent.getOwner()
					.getUserId());
			eventId = be.getEventId();
			assertTrue(be.getBirthdayPerson().equals(
					birthdayEvent.getBirthdayPerson()));
			assertTrue(be.getOwner().equals(birthdayEvent.getOwner()));
			assertTrue(be.getDate().equals(birthdayEvent.getDate()));
			//add birthday event again
			es.addEvent(birthdayEvent);
			connection.rollback();
		} catch (Throwable e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				
			}
			e.printStackTrace();
			if (e instanceof IllegalStateException) {
				throw (IllegalStateException)e;
			}
		} finally {
			ed.deleteEvent(eventId);
		}
	}

	/**
	 * Test method for
	 * {@link edu.cmu.tsp6.serviceImplementations.EventServiceImpl#getUser(java.lang.String)}
	 * .
	 */
	@Test
	public void testGetUser() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link edu.cmu.tsp6.serviceImplementations.EventServiceImpl#updateEvent(edu.cmu.tsp6.bo.Event)}
	 * .
	 */
	@Test
	public void testUpdateEvent() {
		fail("Not yet implemented");
	}

}
