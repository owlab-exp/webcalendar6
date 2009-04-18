package edu.cmu.tsp6.client.enryPoints;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import edu.cmu.tsp6.client.EventService;
import edu.cmu.tsp6.client.EventServiceAsync;
import edu.cmu.tsp6.client.bo.BirthdayEvent;
import edu.cmu.tsp6.client.bo.User;

public class AddEditEntryPoint implements EntryPoint {
	private VerticalPanel mainPanel = new VerticalPanel();
	private FlexTable formFlexTable = new FlexTable();
	private HorizontalPanel buttonPanel = new HorizontalPanel();
	private Button addEventButton = new Button("Add");

	// labels and textboxes
	private Label ownerNameLabel = new Label("Owner: ");
	private Label ownerNameValue = new Label();

	private Label birthDateLabel = new Label("Date: ");
	private TextBox birthDateTextBox = new TextBox();

	private Label birthdayPersonLabel = new Label("Birthday Person: ");
	private TextBox birthdayPersonTextBox = new TextBox();

	
	private EventServiceAsync eventSvcAsynch = GWT.create(EventService.class);
	@Override
	public void onModuleLoad() {
		// Create table for form data.
		formFlexTable.setWidget(0, 0, ownerNameLabel);
		formFlexTable.setWidget(0, 1, ownerNameValue);
		formFlexTable.setWidget(1, 0, birthdayPersonLabel);
		formFlexTable.setWidget(1, 1, birthdayPersonTextBox);
		formFlexTable.setWidget(2, 0, birthDateLabel);
		formFlexTable.setWidget(2, 1, birthDateTextBox);
		//create an event
		final BirthdayEvent e = new BirthdayEvent();
		
		//get the current user from session
		eventSvcAsynch.getCurrentUser(new AsyncCallback<User>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(User result) {
				e.setOwner(result);
			}

							
							
						}
						);
		// Assemble button panel.
		buttonPanel.add(addEventButton);
		addEventButton.addClickHandler(
				new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						System.out.println("add button clicked");
						SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/YYYY");
						
						try {
							e.setDate(dateFormat.parse(birthDateTextBox.getText()));
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						//get the Birthday person from the database
						User birthdayPerson = null;
						
						e.setBirthdayPerson(birthdayPerson);
						
						//check if all fields are filled in
						
						//add an event
						eventSvcAsynch.addEvent(e, new AsyncCallback<Void>() {

							@Override
							public void onFailure(Throwable caught) {
								
								
							}

							@Override
							public void onSuccess(Void result) {
								// TODO Auto-generated method stub
								
							}
							
						}
						);
					}
				     
				}

		);
		// Assemble Main panel.
		mainPanel.add(formFlexTable);
		mainPanel.add(buttonPanel);

		// Associate the Main panel with the HTML host page.
		RootPanel.get("addEditComponent").add(mainPanel);
		birthdayPersonTextBox.setFocus(true);

	}

}
