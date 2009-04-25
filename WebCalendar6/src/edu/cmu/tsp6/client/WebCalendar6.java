package edu.cmu.tsp6.client;

import java.util.Date;
import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.InvocationException;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;

import edu.cmu.tsp6.client.bo.BirthdayEvent;
import edu.cmu.tsp6.client.bo.MonthEnum;
import edu.cmu.tsp6.client.composite.CalendarWidget;
import edu.cmu.tsp6.client.composite.MonthChangeListener;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class WebCalendar6 implements EntryPoint {
	private String SCREEN_WIDTH = "100%";

	private BirthdayEventServiceAsync birthdayEventSvcAsynch = GWT.create(BirthdayEventService.class);
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		DockPanel dock = new DockPanel();
		
		MenuBar menu = createMenuBar();
		
		dock.add(menu, DockPanel.NORTH);
		dock.setWidth(SCREEN_WIDTH);
			
		final CalendarWidget c = new CalendarWidget();
		dock.add(c, DockPanel.CENTER);
		
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
						
						System.out.println(result);
					}
					
				});
			}
		};
		
		//Add Listener
		c.addMonthChangesListener(mcl);
		
		//Init
		c.init();
		
		RootPanel.get("main").add(dock);
	}
	
	private MenuBar createMenuBar() {
		MenuBar menu = new MenuBar();
	    menu.setAutoOpen(true);
	    
		menu.addItem("Add Event", new AddBirthdayEventCommand());
		menu.addItem("Edit Profile", new EditProfileCommand());
		menu.addItem("Logout", new LogoutCommand());
		
	    return menu; 
	}
	
	/////// COMMANDS //////
	
	class AddBirthdayEventCommand implements Command {
		@Override
		public void execute() {
			PopupPanel simplePopup = new PopupPanel(true);
			simplePopup.setTitle("Add Event");
			
			simplePopup.setWidget(new AddEditPanelWidget(simplePopup));
			simplePopup.show();
		}
	}
	
	class EditProfileCommand implements Command {
		@Override
		public void execute() {
			PopupPanel simplePopup = new PopupPanel(true);
			simplePopup.setWidget(new HTML("EditProfileCommand"));
			simplePopup.show();
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
//					messageTextLabel.setText("Login failure: " + caught.getClass().getName());
//					loginSubmitButton.setEnabled(true);
//					userIdTextBox.setFocus(true);
//					try {
//						throw caught;
//					} catch(InvocationException ie) {
//						messageTextLabel.setText("Internal error occurred");
//						loginSubmitButton.setEnabled(true);
//					} catch(LoginFailureException lfe) {
//						messageTextLabel.setText("Login failed: " + lfe.getMessage());
//						loginSubmitButton.setEnabled(true);
//					} catch(Throwable t) {
//						messageTextLabel.setText("Unexpected error occurred");
//						loginSubmitButton.setEnabled(true);
//					}

					}
					public void onSuccess(String result) {
						//message has to be sent to another message field
						System.out.println("Logout successed");
						
						
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
