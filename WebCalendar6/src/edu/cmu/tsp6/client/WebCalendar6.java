package edu.cmu.tsp6.client;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;

import edu.cmu.tsp6.client.bo.BirthdayEvent;
import edu.cmu.tsp6.client.bo.MonthEnum;
import edu.cmu.tsp6.client.composite.CalendarWidget;
import edu.cmu.tsp6.client.composite.MonthChangeListener;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class WebCalendar6 implements EntryPoint {
	private String SCREEN_WIDTH = "80%";
	private FlexTable flexTable;
	private CalendarWidget c = new CalendarWidget();
	private BirthdayEventServiceAsync birthdayEventSvcAsynch = GWT.create(BirthdayEventService.class);
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		DockPanel dock = new DockPanel();
		
		MenuBar menu = createMenuBar();
		
		dock.add(menu, DockPanel.NORTH);
		dock.setWidth(SCREEN_WIDTH);
			
		
		dock.add(c, DockPanel.CENTER);
		dock.setCellHorizontalAlignment(c, HasHorizontalAlignment.ALIGN_CENTER);
		
		ServiceDefTarget endpoint = (ServiceDefTarget) birthdayEventSvcAsynch;
		endpoint.setServiceEntryPoint(GWT.getModuleBaseURL() + "birthdayEvents");
		
		//Listen for month changes
		MonthChangeListener mcl = new MonthChangeListener() {
			@Override
			public void monthChanged(int newMonth, int newYear) {
				birthdayEventSvcAsynch.getBirthdayEventsInMonth(MonthEnum.map(newMonth), new AsyncCallback<List<BirthdayEvent>>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onSuccess(List<BirthdayEvent> result) {
						c.setEvents(result);
						initList();
					}
					
				});
			}
		};
		
		//Add Listener
		c.addMonthChangesListener(mcl);
		
		//Init
		c.init();
		
		RootPanel.get("main").add(dock);
		initList();
	}
	
	private MenuBar createMenuBar() {
		MenuBar menu = new MenuBar();
	    menu.setAutoOpen(true);
	    
		menu.addItem("Add Event", new AddBirthdayEventCommand());
		menu.addItem("Edit Profile", new EditProfileCommand());
		menu.addItem("Logout", new LogoutCommand());
		
	    return menu; 
	}
	/**
	 * Shows Upcoming events after the login.
	 */
	private void initList(){
		//flexTable.clear();
		if (flexTable==null){
			flexTable = new FlexTable();
			
		} else {
			/*
			int rows = flexTable.getRowCount();
			System.out.println("rows:"+rows);
			for(int i=0 ;i<rows;i++){
				//System.out.println("deleting:"+(i-1));
				flexTable.removeRow(0);
			}
			*/
			flexTable.removeFromParent();
			flexTable = new FlexTable();
		}
		
		
		flexTable.setStyleName("wc-listview");
		
		flexTable.setCellSpacing(0);
		flexTable.setCellPadding(4);
		
		// Show Upcoming Events List
		birthdayEventSvcAsynch.getUpcomingBirthdayEvents(new Date(), 1, new AsyncCallback<List<BirthdayEvent>>() {
		
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(List<BirthdayEvent> result) {

				DateTimeFormat dtf = DateTimeFormat.getFormat("yyyy-MM-dd");
									
				//int numRows = flexTable.getRowCount();
				// Add some text
				FlexCellFormatter cellFormatter = flexTable.getFlexCellFormatter();
			    flexTable.setHTML(0, 0, "Upcoming Events");
			    cellFormatter.setStyleName(0, 0, "wc-listview-header");
			    cellFormatter.setColSpan(0, 0, 3);

				Iterator<BirthdayEvent> it = result.iterator();
				int i = 1;
				while(it.hasNext()){
					BirthdayEvent event = it.next();
					//flexTable.setText(i, 0, event.getDate().toString());
					flexTable.setText(i, 0, dtf.format(event.getDate()));
					flexTable.setText(i, 1, event.getBirthdayPerson().getName());
					flexTable.setText(i, 2, "Birthday");
					i++;
				}
				System.out.println(result);
			}
		});

		// Return the panel
		flexTable.ensureDebugId("cwFlexTable");
		RootPanel.get("list").add(flexTable);
	}
	
	/////// COMMANDS //////
	
	class AddBirthdayEventCommand implements Command {
		@Override
		public void execute() {
			PopupPanel simplePopup = new PopupPanel(true);
			simplePopup.setTitle("Add Event");
			
			simplePopup.setWidget(new AddEditPanelWidget(simplePopup, c));
			simplePopup.center();
			simplePopup.show();
			
		}
	}
	
	class EditProfileCommand implements Command {
		@Override
		public void execute() {
			PopupPanel profilePopup = new PopupPanel(true);
			profilePopup.setTitle("Edit Profile");
			profilePopup.setWidget(new UpdateUserWidget(profilePopup));
			profilePopup.center();
			profilePopup.show();
		}
	}
	
	class LogoutCommand implements Command {
		@Override
		public void execute() {
//			PopupPanel simplePopup = new PopupPanel(true);
//			simplePopup.setWidget(new HTML("LogoutCommand"));
//			simplePopup.show();
			final LogoutServiceAsync logoutService = GWT.create(LogoutService.class);
			try {
				logoutService.logoutServer(new AsyncCallback<String>(){
					public void onFailure(Throwable caught) {
						System.out.println("Logout failed because " + caught.getMessage());
					}
					public void onSuccess(String result) {
						RootPanel mainPanel = RootPanel.get("main");
						RootPanel listPanel = RootPanel.get("list");
						//RootPanel loginPanel = RootPanel.get("login");
						//message has to be sent to another message field
						System.out.println("Logout successed");
						//loginPanel.removeFromParent();
						mainPanel.setVisible(false);
						listPanel.setVisible(false);
						
						Panel loginPanel = Globals.getPanel("loginPanel");
						///loginPanel.g
						loginPanel.setVisible(true);
						//loginPanel.
						
					}
				});
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	class EditEventCommand implements Command {
		private int eventId;
		
		public EditEventCommand(int eventId) {
			this.eventId = eventId;
		}
		
		@Override
		public void execute() {
			PopupPanel simplePopup = new PopupPanel(true);
			simplePopup.setWidget(new HTML("EditEvent ID=" + eventId));
			simplePopup.show();
		}
	}
}
