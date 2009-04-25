package edu.cmu.tsp6.client;

import java.util.Date;
import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import edu.cmu.tsp6.client.bo.BirthdayEvent;
import edu.cmu.tsp6.client.bo.MonthEnum;

/**
 * This service provides information about birthday events
 * 
 * @author Varokas
 */
public interface BirthdayEventServiceAsync {

	void getUpcomingBirthdayEvents(Date fromDate, int months,
			AsyncCallback<List<BirthdayEvent>> callback);

	void getBirthdayEventsInMonth(MonthEnum month,
			AsyncCallback<List<BirthdayEvent>> callback);
}
