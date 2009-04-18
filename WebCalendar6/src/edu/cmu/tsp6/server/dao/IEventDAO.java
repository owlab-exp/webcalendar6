package edu.cmu.tsp6.server.dao;

import java.util.List;

import edu.cmu.tsp6.client.bo.BirthdayEvent;
import edu.cmu.tsp6.server.dao.exception.EventExistException;
import edu.cmu.tsp6.server.dao.exception.EventNotExistException;

public interface IEventDAO {
	/**
	 * Add new event to the system.
	 * 
	 * @param newEvent
	 *            New event (the id field will be ignored. it is assigned by the
	 *            system).
	 * @throws EventExistException
	 *             If the given eventId exists in the system
	 */
	public void addEvent(BirthdayEvent newEvent) throws EventExistException;

	/**
	 * Edit events's data
	 * 
	 * @param existingEvent
	 *            event data to be updated
	 * @throws UserNotExistException
	 *             If the given event's id does not exist in the system
	 */
	public void editEvent(BirthdayEvent existingEvent)
			throws EventNotExistException;

	/**
	 * Delete the given event Id from the system
	 * 
	 * @param eventId
	 * @throws EventNotExistException
	 *             If the given event id does not exist in the system
	 */
	public void deleteEvent(int eventId) throws EventNotExistException;

	/**
	 * Get the event information from the given id
	 * 
	 * @param eventId
	 * @throws EventNotExistException
	 *             If the given event id does not exist in the system
	 * @return Null if no such event exist
	 */
	public BirthdayEvent getEvent(int eventId) throws EventNotExistException;

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
			int endDay, int endMonth);

}
