package edu.cmu.tsp6.client.composite;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;

import edu.cmu.tsp6.client.bo.BirthdayEvent;
import edu.cmu.tsp6.client.bo.Event;

/**
 * The calendar view. The customizable area would be the main calendar view area
 * which consist of 7x5 blocks In each of these block we can set the date and
 * the list of events.
 * 
 * The calendar will be able to add an
 */
public class CalendarWidget extends Composite {
	public static final String TABLE_STYLE_NAME = "wc-calendarview-flextable";

	private static final String BLOCK_WIDTH = "100px";
	private static final String TITLE_HEIGHT = "50px";
	private static final String BLOCK_HEIGHT = "100px";

	private static final Point POS_PREV = new Point(0, 0);
	private static final Point POS_TITLE = new Point(0, 1);
	private static final Point POS_NEXT = new Point(0, 6);

	List<CalendarBlockWidget> blocks = new ArrayList<CalendarBlockWidget>();

	private Label title;
	private Hyperlink previous;
	private Hyperlink next;

	private int currentMonth;
	private int currentYear;

	private int startAt = 0;
	private List<MonthChangeListener> monthListeners = new LinkedList<MonthChangeListener>();

	public CalendarWidget() {
		// 1. Set Layout - a 12 row, 7 columns flex table
		FlexTable flexTable = new FlexTable();
		FlexCellFormatter cellFormatter = flexTable.getFlexCellFormatter();

		flexTable.addStyleName(TABLE_STYLE_NAME);
		flexTable.setCellSpacing(0);
		flexTable.setCellPadding(0);

		// Prepare first row (<< Prev, Month, Next>>)
		title = new Label("Month");
		previous = new Hyperlink("<<Prev", "");
		next = new Hyperlink("Next>>", "");

		flexTable.setWidget(POS_PREV.row, POS_PREV.col, previous);

		cellFormatter.setColSpan(0, 1, 5);
		cellFormatter.setHeight(0, 1, TITLE_HEIGHT);
		cellFormatter.setHorizontalAlignment(0, 1,
				HasHorizontalAlignment.ALIGN_CENTER);

		flexTable.setWidget(POS_TITLE.row, POS_TITLE.col, title);

		flexTable.setWidget(POS_NEXT.row, POS_NEXT.col, next);

		// Prepare second row (Days)
		cellFormatter.setHeight(1, 0, TITLE_HEIGHT);
		flexTable.setWidget(1, 0, new Label("Sunday"));
		flexTable.setWidget(1, 1, new Label("Monday"));
		flexTable.setWidget(1, 2, new Label("Tuesday"));
		flexTable.setWidget(1, 3, new Label("Wednesday"));
		flexTable.setWidget(1, 4, new Label("Thursday"));
		flexTable.setWidget(1, 5, new Label("Friday"));
		flexTable.setWidget(1, 6, new Label("Saturday"));

		for (int i = 0; i < 6; i++) {
			cellFormatter.setHorizontalAlignment(1, i,
					HasHorizontalAlignment.ALIGN_CENTER);
			cellFormatter.setWidth(1, i, BLOCK_WIDTH);
		}

		// Prepare row blocks
		for (int r = 2; r <= 10; r += 2)
			for (int c = 0; c <= 6; c++) {
				cellFormatter.setRowSpan(r, c, 2);
				cellFormatter.setHeight(r, c, BLOCK_HEIGHT);

				CalendarBlockWidget cbw = new CalendarBlockWidget();
				blocks.add(cbw);
				flexTable.setWidget(r, c, cbw);
			}

		// Calls init widget to the wrapped widget
		initWidget(flexTable);

		Date d = new Date();

		currentMonth = d.getMonth() + 1;
		currentYear = d.getYear() + 1900;

		setDatesArrangement(currentMonth, currentYear);
		
		// Wire Listeners
		previous.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				currentMonth -= 1;
				if(currentMonth == 0) {
					currentMonth = 12;
					currentYear--;
				}
				
				setDatesArrangement(currentMonth, currentYear);
				fireMonthChangesEvent(currentMonth, currentYear);
 			}
		});
		
		next.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				currentMonth += 1;
				if(currentMonth > 12) {
					currentMonth = 1;
					currentYear++;
				}
				
				setDatesArrangement(currentMonth, currentYear);
				fireMonthChangesEvent(currentMonth, currentYear);
 			}
		});
	}

	/**
	 * Set the current date arrangement to start at a specific block in the
	 * calendar. The other dates will be laid out according to number of days in
	 * given month and year
	 * 
	 * @param month
	 *            January = 1
	 * @param year
	 */
	public void setDatesArrangement(int month, int year) {
		// Set Header
		String monthString = null;
		switch (month) {
		case 1:
			monthString = "January";
			break;
		case 2:
			monthString = "February";
			break;
		case 3:
			monthString = "March";
			break;
		case 4:
			monthString = "April";
			break;
		case 5:
			monthString = "May";
			break;
		case 6:
			monthString = "June";
			break;
		case 7:
			monthString = "July";
			break;
		case 8:
			monthString = "August";
			break;
		case 9:
			monthString = "September";
			break;
		case 10:
			monthString = "October";
			break;
		case 11:
			monthString = "November";
			break;
		case 12:
			monthString = "December";
			break;
		}

		title.setText(monthString + " " + year);

		// Set All dates in blocks
		Date d = new Date(year - 1900, month - 1, 1);
		startAt = d.getDay();

		Date d2 = new Date(year - 1900, month, 0);
		int endAt = d2.getDate();

		for (int i = 0; i < blocks.size(); i++) {
			if (i < startAt || i > startAt + endAt - 1)
				blocks.get(i).setDay(-1);
			else
				blocks.get(i).setDay(i - startAt + 1);
		}

		clearEvents();
	}

	public void addMonthChangesListener(MonthChangeListener m) {
		this.monthListeners.add(m);
	}

	/**
	 * Fire the month changes event to all listeners
	 * 
	 * @param newMonth
	 * @param newYear
	 */
	protected void fireMonthChangesEvent(int newMonth, int newYear) {
		for (MonthChangeListener m : monthListeners) {
			m.monthChanged(newMonth, newYear);
		}
	}

	/**
	 * Get the calendar block widget at certain day
	 * 
	 * @param day
	 * @return
	 */
	private CalendarBlockWidget getCalendarBlock(int day) {
		return blocks.get(day + startAt - 1);
	}

	/**
	 * Set the block with given event list
	 * 
	 * @param day
	 * @param events
	 */
	private void setEvent(int day, List<BirthdayEvent> events) {
		getCalendarBlock(day).setEvents(events);
	}

	/**
	 * Clear all events from the calendar view
	 */
	public void clearEvents() {
		for (int i = 0; i < blocks.size(); i++)
			blocks.get(i).setEvents(null);
	}

	/**
	 * Set events in the calendar for the given months. The events that occurred
	 * in different months would be ignored
	 * 
	 * @param events
	 *            The events to be set on calendar
	 */
	public void setEvents(List<BirthdayEvent> events) {
		// Group events into days
		Map<Integer, List<BirthdayEvent>> eventsMap = new HashMap<Integer, List<BirthdayEvent>>();

		for (BirthdayEvent e : events) {
			int date = e.getDate().getDate();

			if (!eventsMap.containsKey(date))
				eventsMap.put(date, new LinkedList<BirthdayEvent>());

			eventsMap.get(date).add(e);
		}

		// call setEvent()
		for (Map.Entry<Integer, List<BirthdayEvent>> eventsEntry : eventsMap.entrySet()) {
			getCalendarBlock(eventsEntry.getKey()).setEvents(
					eventsEntry.getValue());
		}
	}

	static class CalendarBlockWidget extends Composite {
		int day;
		List<Event> events;

		Label l = new Label();
		private DockPanel panel;
		private VerticalPanel eventsPanel;

		public CalendarBlockWidget() {
			// 1. Set Layout
			panel = new DockPanel();
			panel.setSize(BLOCK_WIDTH, BLOCK_HEIGHT);

			// 2. Add in the static widgets
			panel.add(l, DockPanel.NORTH);
			panel.add(eventsPanel, DockPanel.CENTER);
			
			// Calls init widget to the wrapped widget
			initWidget(panel);
		}

		/** Set the day of this calendar block */
		public void setDay(int day) {
			this.day = day;
			
			if (day == -1)
				l.setText("");
			else
				l.setText(String.valueOf(day));
		}

		/** Set events list of this block */
		public void setEvents(List<BirthdayEvent> events) {
			clearEvents();
			
			if(events == null || events.size() == 0)
				return;
			
			for(BirthdayEvent e : events) {
				Hyperlink h = new Hyperlink(e.getBirthdayPerson().getName(), "");
				eventsPanel.add(h);
			}
		}

		public void clearEvents() {
			eventsPanel = new VerticalPanel();
		}
	}

	static class Point {
		public int row;
		public int col;

		public Point(int row, int col) {
			super();
			this.row = row;
			this.col = col;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + col;
			result = prime * result + row;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Point other = (Point) obj;
			if (col != other.col)
				return false;
			if (row != other.row)
				return false;
			return true;
		}
	}
}
