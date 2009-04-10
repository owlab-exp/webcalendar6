package edu.cmu.tsp6.client;

import java.util.Date;
import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;

/**
 * This service provides information about birthday events
 * 
 * @author Varokas
 */
public interface BirthdayEventService extends RemoteService {
	/**
	 * Get upcoming birthday events from specified date and months in the future.
	 * 
	 * <p>
	 * For example, if the given date is Feb 20th and the months given is 2. The 
	 * result will return every birthdate starting at Feb20th midnight, to April
	 * 20th midnight.
	 * </p>
	 * 
	 * @param fromDate The start of a date range, inclusive
	 * @param months Months we are looking into the future.
	 * @return List of birthdays in the specified ranges.
	 */
	public List<BirthdayEvent> getUpcomingBirthdayEvents(Date fromDate, int months);
	
	/**
	 * Get Birthday of a person at a specified month.
	 * 
	 * @param month The month of interest
	 * @return birthday events for people who have birthdat in that month
	 */
	public List<BirthdayEvent> getBirthdayEventsInMonth(int month);
}
