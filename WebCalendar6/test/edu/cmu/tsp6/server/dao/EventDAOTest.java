package edu.cmu.tsp6.server.dao;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.dbunit.Assertion;
import org.dbunit.DatabaseUnitException;
import org.dbunit.dataset.Column;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.IColumnFilter;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.ext.mysql.MySqlConnection;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.cmu.tsp6.bo.BirthdayEvent;
import edu.cmu.tsp6.bo.Event;
import edu.cmu.tsp6.bo.User;
import edu.cmu.tsp6.server.dao.exception.EventExistException;
import edu.cmu.tsp6.server.dao.exception.EventNotExistException;
import edu.cmu.tsp6.server.dao.exception.UserExistException;
import edu.cmu.tsp6.server.dao.exception.UserNotExistException;

/**
 * Test {@link UserDAO}
 * @author Varokas
 *
 */
public class EventDAOTest {
	private static final String FILE_INIT = "res/test/eventdao/init.xml";
	private static final String FILE_ADD = "res/test/eventdao/add.xml";
	private static final String FILE_EDIT = "res/test/eventdao/edit.xml";
	private static final String FILE_DELETE = "res/test/eventdao/delete.xml";
	
	private static final String SCHEMA = "CALENDARDB";

	private static MySqlConnection dbUnitConnection;
	
	@BeforeClass
	public static void beforeClass() throws DatabaseUnitException {
		dbUnitConnection = new MySqlConnection(DatabaseConnection.getInstance().getConnection(), SCHEMA);
	}
	
	@Before
	public void before() throws Exception{
		//Insert Init
		IDataSet dataset = new FlatXmlDataSet(new FileInputStream(FILE_INIT));
		DatabaseOperation.CLEAN_INSERT.execute(dbUnitConnection, dataset);
	}
	
//	@Test
//	public void addEventTest() throws Exception {
//		//Create One event
//		BirthdayEvent birthdayEvent = new BirthdayEvent();
//		User owner = new User();
//		User birthdayPerson = new User();
//		
//		owner.setUserId("abin");
//		birthdayPerson.setUserId("non");
//		
//		birthdayEvent.setOwner(owner);
//		birthdayEvent.setBirthdayPerson(birthdayPerson);
//		birthdayEvent.setDate(new SimpleDateFormat("yyyy-MM-dd").parse("1984-01-25"));
//		
//		//Add User
//		EventDAO.getInstance().addEvent(birthdayEvent);
//		
//		//Assert
//		ITable actualTable = dbUnitConnection.createDataSet().getTable("EVENT");
//		ITable expectedTable = new FlatXmlDataSet(new FileInputStream(FILE_ADD)).getTable("EVENT");
//
//		Assertion.assertEquals(expectedTable, actualTable);
//	}
	
	@Test
	public void editEvent() throws Exception {		
		//Create One event
		BirthdayEvent birthdayEvent = new BirthdayEvent();
		User owner = new User();
		User birthdayPerson = new User();
		
		owner.setUserId("non");
		birthdayPerson.setUserId("abin");
		
		birthdayEvent.setEventId(1);
		birthdayEvent.setOwner(owner);
		birthdayEvent.setBirthdayPerson(birthdayPerson);
		birthdayEvent.setDate(new SimpleDateFormat("yyyy-MM-dd").parse("2009-05-29"));
		
		//Edit User
		EventDAO.getInstance().editEvent(birthdayEvent);
		
		//Assert
		ITable actualTable = dbUnitConnection.createDataSet().getTable("EVENT");
		ITable expectedTable = new FlatXmlDataSet(new FileInputStream(FILE_EDIT)).getTable("EVENT");

		Assertion.assertEquals(expectedTable, actualTable);
	}
	
	@Test(expected=EventNotExistException.class)
	public void editEventNotExist() throws Exception {		
		//Create One event
		BirthdayEvent birthdayEvent = new BirthdayEvent();
		User owner = new User();
		User birthdayPerson = new User();
		
		owner.setUserId("asgasg");
		birthdayPerson.setUserId("abin");
		
		birthdayEvent.setOwner(owner);
		birthdayEvent.setBirthdayPerson(birthdayPerson);
		birthdayEvent.setDate(new SimpleDateFormat("yyyy-MM-dd").parse("2009-05-29"));

		
		//Edit User
		EventDAO.getInstance().editEvent(birthdayEvent);

	}
	
	@Test
	public void deleteEvent() throws Exception {
		//Delete Event
		EventDAO.getInstance().deleteEvent(2);
		
		//Assert
		ITable actualTable = dbUnitConnection.createDataSet().getTable("EVENT");
		ITable expectedTable = new FlatXmlDataSet(new FileInputStream(FILE_DELETE)).getTable("EVENT");

		Assertion.assertEquals(expectedTable, actualTable);
	}
	
	@Test(expected=EventNotExistException.class)
	public void deleteEventNotExist() throws Exception {	
		//Delete Event
		EventDAO.getInstance().deleteEvent(99999);
	}
	
	@Test
	public void getEventTest() throws Exception {
		//Get Event
		BirthdayEvent event = EventDAO.getInstance().getEvent(1);
		
		assertNotNull(event);
		assertEquals("non", event.getOwner().getUserId());
		assertEquals("abin", event.getBirthdayPerson().getUserId());
		assertEquals(java.sql.Date.valueOf("2009-05-25"), event.getDate());
	}
	
	@Test
	public void getEventsTest() throws Exception {
		//Get Events
		List<BirthdayEvent> events = EventDAO.getInstance().getEvents(1,4,30,5);
		
		assertNotNull(events);
		assertEquals(1, events.size());
	}
}
