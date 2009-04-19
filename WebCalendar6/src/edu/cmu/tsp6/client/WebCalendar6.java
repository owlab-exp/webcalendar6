package edu.cmu.tsp6.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class WebCalendar6 implements EntryPoint {
	private String SCREEN_WIDTH = "100%";
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		DockPanel dock = new DockPanel();
		
		MenuBar menu = createMenuBar();
		
		dock.add(menu, DockPanel.NORTH);
		dock.setWidth(SCREEN_WIDTH);
		
		//TEMP HyperLink
		Hyperlink hl = new Hyperlink();
		hl.setText("Suppose that this is an event edit link (let id=4)");
		
		hl.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				new EditEventCommand(4).execute();
			}
		});
		dock.add(hl, DockPanel.CENTER);
		
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
			PopupPanel simplePopup = new PopupPanel(true);
			simplePopup.setWidget(new HTML("LogoutCommand"));
			simplePopup.show();
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
