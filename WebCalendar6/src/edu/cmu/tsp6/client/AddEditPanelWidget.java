package edu.cmu.tsp6.client;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DatePicker;

import edu.cmu.tsp6.client.bo.BirthdayEvent;
import edu.cmu.tsp6.client.bo.User;
import edu.cmu.tsp6.client.composite.CalendarWidget;

public class AddEditPanelWidget extends VerticalPanel {

	FlexTable formFlexTable = new FlexTable();
	Label msgLabel = new Label();
	
	HorizontalPanel buttonPanel = new HorizontalPanel();
	Button addEventButton = new Button("Add");

	// labels and textboxes
	Label ownerNameLabel = new Label("Owner: ");
	Label ownerNameValue = new Label();

	Label birthDateLabel = new Label("Date: ");
	TextBox birthDateTextbox = new TextBox();

	Label birthdayPersonLabel = new Label("Birthday Person: ");
	TextBox birthdayPersonTextBox = new TextBox();
	DatePicker datePicker = new DatePicker();

	EventServiceAsync eventSvcAsynch = GWT.create(EventService.class);

	public AddEditPanelWidget(final PopupPanel simplePopup, final CalendarWidget c) {
		super();
		// Create table for form data.
		formFlexTable.setWidget(0, 0, ownerNameLabel);
		formFlexTable.setWidget(0, 1, ownerNameValue);
		formFlexTable.setWidget(1, 0, birthdayPersonLabel);
		formFlexTable.setWidget(1, 1, birthdayPersonTextBox);
//		formFlexTable.setWidget(1, 3, birthdayPersonNameLabel);
		formFlexTable.setWidget(2, 0, birthDateLabel);
		formFlexTable.setWidget(2, 1, birthDateTextbox);
		birthDateTextbox.setReadOnly(true);
		formFlexTable.setWidget(2, 2, datePicker);
		formFlexTable.setWidget(3,1, msgLabel);
		datePicker.setVisible(false);
		
		// create an event
		final BirthdayEvent e = new BirthdayEvent();
		ServiceDefTarget endpoint = (ServiceDefTarget) eventSvcAsynch;
		endpoint.setServiceEntryPoint(GWT.getModuleBaseURL() + "events");
		// get the current user from session
		eventSvcAsynch.getCurrentUser(new AsyncCallback<User>() {

			@Override
			public void onFailure(Throwable caught) {
				
				msgLabel.setText(caught.getMessage());
			}

			@Override
			public void onSuccess(User result) {
				msgLabel.setText("");
				// fake user code
				if (result == null) {
					result = new User();
					result.setUserId("coconut");
					result.setName("Cocos");
				}
				e.setOwner(result);
				ownerNameValue.setText(result.getName());
			}

		});
		// Assemble button panel.
		datePicker.addValueChangeHandler(new ValueChangeHandler<Date>() {

			@Override
			public void onValueChange(ValueChangeEvent<Date> event) {
				Date selectedDate = event.getValue();
				
				birthDateTextbox.setText(DateTimeFormat.getShortDateFormat().format(selectedDate));
				e.setDate(selectedDate);
			}

		});
		buttonPanel.add(addEventButton);
		birthDateTextbox.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				datePicker.setVisible(true);

			}

		});
		addEventButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				System.out.println("add button clicked");
				
				// get the Birthday person from the database
				final String birthdayPersonName = birthdayPersonTextBox.getText();
				eventSvcAsynch.getUser(birthdayPersonName,
						new AsyncCallback<User>() {

							@Override
							public void onFailure(Throwable caught) {
								Window.alert("User " + birthdayPersonName + "  is not valid. Please enter a valid user name");

							}

							@Override
							public void onSuccess(User result) {
								System.out.println("adding  " + result);
								e.setBirthdayPerson(result);
								// check if all fields are filled in

								// add an event
								eventSvcAsynch.addEvent(e,
										new AsyncCallback<Void>() {

											@Override
											public void onFailure(
													Throwable caught) {
												
												Window.alert("User " + e.getBirthdayPerson().getName() + "  already has a birthday in the system. Please remove that birthday event to add a new one.");
											}

											@Override
											public void onSuccess(Void result) {
												msgLabel.setText("Adding succesful");
												
												simplePopup.hide(true);
												if (c!=null) {
													c.init();
												}
											}

										});

							}

						});

			}

		}

		);
		// Assemble Main panel.
		// this.add(new HTML("<title>Add Birthday Event</title>"));
		this.add(formFlexTable);
		this.add(buttonPanel);

		// Associate the Main panel with the HTML host page.
		// RootPanel.get("addEditComponent").add(mainPanel);
		birthdayPersonTextBox.setFocus(true);

	}

}
