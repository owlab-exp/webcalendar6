package edu.cmu.tsp6.server.service;

import java.text.SimpleDateFormat;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;

import edu.cmu.tsp6.client.bo.MonthEnum;
import edu.cmu.tsp6.server.dao.IEventDAO;

public class BirthdayEventServiceImplTest {

	private BirthdayEventServiceImpl birthdayEventService;

	@Before
	public void before() {
		birthdayEventService = new BirthdayEventServiceImpl();
	}

	@Test
	public void testGetBirthdayEventsInMonth() {
		Mockery context = new Mockery();
		final IEventDAO eventDao = (IEventDAO) context.mock(IEventDAO.class);

		context.checking(new Expectations() {
			{
				oneOf(eventDao).getEvents(1, 1, 31, 1); // January
				oneOf(eventDao).getEvents(1, 2, 29, 2); // February
				oneOf(eventDao).getEvents(1, 3, 31, 3); // March
				oneOf(eventDao).getEvents(1, 4, 30, 4); // April
				oneOf(eventDao).getEvents(1, 5, 31, 5); // May
				oneOf(eventDao).getEvents(1, 6, 30, 6); // June
				oneOf(eventDao).getEvents(1, 7, 31, 7); // July
				oneOf(eventDao).getEvents(1, 8, 31, 8); // August
				oneOf(eventDao).getEvents(1, 9, 30, 9); // September
				oneOf(eventDao).getEvents(1, 10, 31, 10); // October
				oneOf(eventDao).getEvents(1, 11, 30, 11); // November
				oneOf(eventDao).getEvents(1, 12, 31, 12); // December
			}
		});

		// Set DAO
		birthdayEventService.setEventDAO(eventDao);

		// Execute
		for (MonthEnum monthEnum : MonthEnum.values()) {
			birthdayEventService.getBirthdayEventsInMonth(monthEnum);
		}

		// Assert
		context.assertIsSatisfied();
	}

	@Test
	public void testGetUpcomingBirthdayEvents() throws Exception {
		Mockery context = new Mockery();
		final IEventDAO eventDao = (IEventDAO) context.mock(IEventDAO.class);

		context.checking(new Expectations() {
			{
				// From May 3, two months
				oneOf(eventDao).getEvents(3, 5, 3, 7);

				// From December 25th, one month
				oneOf(eventDao).getEvents(25, 12, 25, 1);

				// From January 30th, one month
				oneOf(eventDao).getEvents(30, 1, 29, 2);
				
				// From March 31th, three month
				oneOf(eventDao).getEvents(31, 3, 30, 6);
			}
		});

		// Set DAO
		birthdayEventService.setEventDAO(eventDao);

		// Execute
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		birthdayEventService.getUpcomingBirthdayEvents(df.parse("2009-05-03"), 2);
		birthdayEventService.getUpcomingBirthdayEvents(df.parse("2009-12-25"), 1);
		birthdayEventService.getUpcomingBirthdayEvents(df.parse("2009-01-30"), 1);
		birthdayEventService.getUpcomingBirthdayEvents(df.parse("2009-03-31"), 3);
		
		// Assert
		context.assertIsSatisfied();
	}

}
