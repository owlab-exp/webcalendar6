package edu.cmu.tsp6.server.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import edu.cmu.tsp6.client.bo.BirthdayEvent;
import edu.cmu.tsp6.client.bo.User;
import edu.cmu.tsp6.server.dao.exception.EventExistException;
import edu.cmu.tsp6.server.dao.exception.EventNotExistException;
import edu.cmu.tsp6.server.dao.exception.UserNotExistException;

public class EventDAO implements IEventDAO {
	private static EventDAO instance;

	private EventDAO() {
	}

	public static EventDAO getInstance() {
		if (instance == null)
			instance = new EventDAO();

		return instance;
	}

	private String getSQLDate(java.util.Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 String dateString = sdf.format(date);

//		return Date.valueOf(c.get(Calendar.YEAR) + "-"
//				+ (c.get(Calendar.MONTH) + 1)  + "-" + c.get(Calendar.DATE));
		 return dateString;
	}

	private java.util.Date getUtilDate(java.sql.Date date) {
		return (java.util.Date) date;
	}

	private boolean isEventExist(int eventId) {
		String sql = String.format("SELECT * FROM EVENT where EVENT_ID = '%s'",
				eventId);
		Statement s = DatabaseConnection.getInstance().getStatement();

		try {
			s.execute(sql);
			return (s.getResultSet().next());
		} catch (SQLException e) {
			e.printStackTrace();

			return false;
		}

	}

	/**
	 * Add new event to the system.
	 * 
	 * @param newEvent
	 *            New event (the id field will be ignored. it is assigned by the
	 *            system).
	 * @throws EventExistException
	 *             If the given eventId exists in the system
	 */
	public void addEvent(BirthdayEvent newEvent) throws EventExistException {
		if (isEventExist(newEvent.getEventId()))
			throw new EventExistException();

		String sql = String
				.format(
						"INSERT INTO EVENT(EVENT_TYPE,EVENT_OWNER_ID,EVENT_BIRTH_PERSON_ID,EVENT_DATE) VALUES ('%s','%s','%s','%s')",
						"birthday", newEvent.getOwner().getUserId(), newEvent
								.getBirthdayPerson().getUserId(),
						getSQLDate(newEvent.getDate()));

		try {
			DatabaseConnection.getInstance().getStatement().execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Edit events's data
	 * 
	 * @param existingEvent
	 *            event data to be updated
	 * @throws UserNotExistException
	 *             If the given event's id does not exist in the system
	 */
	public void editEvent(BirthdayEvent existingEvent)
			throws EventNotExistException {
		if (!isEventExist(existingEvent.getEventId()))
			throw new EventNotExistException();

		String sql = String
				.format(
						"UPDATE EVENT SET EVENT_DATE='%s' WHERE EVENT_ID='%d'",
						getSQLDate(existingEvent.getDate()), existingEvent
								.getEventId());
		try {
			DatabaseConnection.getInstance().getStatement().execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Delete the given event Id from the system
	 * 
	 * @param eventId
	 * @throws EventNotExistException
	 *             If the given event id does not exist in the system
	 */
	public void deleteEvent(int eventId) throws EventNotExistException {
		if (!isEventExist(eventId))
			throw new EventNotExistException();

		String sql = String.format("DELETE FROM EVENT WHERE EVENT_ID='%d' ",
				eventId);

		try {
			DatabaseConnection.getInstance().getStatement().execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Get the event information from the given id
	 * 
	 * @param eventId
	 * @throws EventNotExistException
	 *             If the given event id does not exist in the system
	 * @return Null if no such event exist
	 */
	public BirthdayEvent getEvent(int eventId) throws EventNotExistException {
		if (!isEventExist(eventId))
			throw new EventExistException();

		String sql = String.format("SELECT * FROM EVENT WHERE EVENT_ID='%d' ",
				eventId);

		try {
			ResultSet rs = DatabaseConnection.getInstance().getStatement()
					.executeQuery(sql);
			rs.next();

			BirthdayEvent event = new BirthdayEvent();
			event.setEventId(rs.getInt("EVENT_ID"));
			event.setOwner(UserDAO.getInstance().getUser(
					rs.getString("EVENT_OWNER_ID")));
			event.setBirthdayPerson(UserDAO.getInstance().getUser(
					rs.getString("EVENT_BIRTH_PERSON_ID")));
			event.setDate(DateUtil.sql2java(rs.getDate("EVENT_DATE")));

			return event;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Get the event information from the given id
	 * 
	 * @param eventId
	 * @throws EventNotExistException
	 *             If the given event id does not exist in the system
	 * @return Null if no such event exist
	 */
	public BirthdayEvent getEventByOwner(String owner) throws EventNotExistException {
		
		String sql = String.format("SELECT * FROM EVENT WHERE EVENT_OWNER_ID='" + owner+ "'");

		try {
			ResultSet rs = DatabaseConnection.getInstance().getStatement()
					.executeQuery(sql);
			rs.next();

			BirthdayEvent event = new BirthdayEvent();
			event.setEventId(rs.getInt("EVENT_ID"));
			event.setOwner(UserDAO.getInstance().getUser(
					rs.getString("EVENT_OWNER_ID")));
			event.setBirthdayPerson(UserDAO.getInstance().getUser(
					rs.getString("EVENT_BIRTH_PERSON_ID")));
			event.setDate(DateUtil.sql2java(rs.getDate("EVENT_DATE")));

			return event;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Get Information of all events in the system in the given range, inclusive
	 * 
	 * @param startDay
	 * @param startMonth
	 *            January = 1
	 * @param endDay
	 * @param endMonth
	 * @return List of events or empty list if there's no events.
	 */
	public List<BirthdayEvent> getEvents(int startDay, int startMonth,
			int endDay, int endMonth) {
		List<BirthdayEvent> events = new ArrayList<BirthdayEvent>();

		//Get All
		String sql = String.format("SELECT * FROM EVENT order by EVENT_DATE");
		
		//Create Date

		try {
			ResultSet rs = DatabaseConnection.getInstance().getStatement()
					.executeQuery(sql);
			while (rs.next()) {
				// Filter
				Date date = rs.getDate("EVENT_DATE");
				GregorianCalendar c = new GregorianCalendar();
				c.setTime(date);
				
				int month = c.get(Calendar.MONTH) + 1;
				int day = c.get(Calendar.DATE);
				
				if(month < startMonth || month > endMonth)
					continue;
				
				if(month == startMonth && day < startDay) continue;
				if(month == endMonth && day > endDay) continue;
				
				BirthdayEvent event = new BirthdayEvent();
				event.setEventId(rs.getInt("EVENT_ID"));
				event.setOwner(UserDAO.getInstance().getUser(
						rs.getString("EVENT_OWNER_ID")));
				event.setBirthdayPerson(UserDAO.getInstance().getUser(
						rs.getString("EVENT_BIRTH_PERSON_ID")));
				
				
				
				event.setDate(DateUtil.sql2java(rs.getDate("EVENT_DATE")));

				events.add(event);
			}

			return events;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean exitsEventByBirthdayPerson(User birthdayPerson) {
		String sql = String.format("SELECT * FROM EVENT where EVENT_BIRTH_PERSON_ID = '%s'",
				birthdayPerson.getUserId());
		Statement s = DatabaseConnection.getInstance().getStatement();

		try {
			s.execute(sql);
			return (s.getResultSet().next());
		} catch (SQLException e) {
			e.printStackTrace();

			return false;
		}
	}
}
