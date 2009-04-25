package edu.cmu.tsp6.server.serviceImplementations;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import edu.cmu.tsp6.client.BirthdayEventService;
import edu.cmu.tsp6.client.bo.BirthdayEvent;
import edu.cmu.tsp6.client.bo.MonthEnum;
import edu.cmu.tsp6.server.dao.EventDAO;
import edu.cmu.tsp6.server.dao.IEventDAO;

@RemoteServiceRelativePath("birthdayEvents")
public class BirthdayEventServiceImpl extends RemoteServiceServlet  implements BirthdayEventService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private IEventDAO eventDAO;

	public BirthdayEventServiceImpl() {
		this.eventDAO = EventDAO.getInstance();
	}

	public IEventDAO getEventDAO() {
		return eventDAO;
	}

	public void setEventDAO(IEventDAO eventDAO) {
		this.eventDAO = eventDAO;
	}

	/**
	 * Find last day of given month
	 * 
	 * @param month
	 *            January = 1
	 * @return
	 */
	private int findLastDayOfMonth(int month) {
		switch (month) {
		case 1:
			return 31;
		case 2:
			return 29;
		case 3:
			return 31;
		case 4:
			return 30;
		case 5:
			return 31;
		case 6:
			return 30;
		case 7:
			return 31;
		case 8:
			return 31;
		case 9:
			return 30;
		case 10:
			return 31;
		case 11:
			return 30;
		case 12:
			return 31;
		default:
			return 0;
		}
	}

	@Override
	public List<BirthdayEvent> getBirthdayEventsInMonth(MonthEnum month) {
		// 0. get month number from month enum
		int monthNumber = month.getMonthNumber();

		// 1. Find the last day of the month. Using Calendar methods
		int lastDay = findLastDayOfMonth(monthNumber);

		// 2. Query the events starting from day 1 to the last day of month
		List<BirthdayEvent> birthdayEvents = eventDAO.getEvents(1, monthNumber,
				lastDay, monthNumber);

		return birthdayEvents;
	}

	@Override
	public List<BirthdayEvent> getUpcomingBirthdayEvents(Date fromDate,
			int months) {
		// Extract Day and month from the fromdate
		GregorianCalendar c = new GregorianCalendar();
		c.setTime(fromDate);

		int startDay = c.get(Calendar.DATE);
		int startMonth = c.get(Calendar.MONTH) + 1;

		// Add months to the startMonth and round it
		int endMonth = startMonth + months;
		if(endMonth > 12)
			endMonth -= 12;

		// Check if the months exceed the last day of month
		int endDay = startDay;
		
		int lastDayOfMonth = findLastDayOfMonth(endMonth);
		if(lastDayOfMonth < endDay)
			endDay = lastDayOfMonth;

		return eventDAO.getEvents(startDay, startMonth, endDay, endMonth);
	}

}
