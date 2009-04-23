package edu.cmu.tsp6.client;
 
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler; 
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.VerticalPanel; 
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;

import edu.cmu.tsp6.client.bo.User;
import edu.cmu.tsp6.client.bo.NewUser;

public class AddUserWidget extends VerticalPanel {

	private FlexTable formFlexTable = new FlexTable();
	private HorizontalPanel buttonPanel = new HorizontalPanel();
	private Button addUserButton = new Button("Registry");
	private Button cancelButton = new Button("Cancel");

	// labels and textboxes
	private Label userIDLabel = new Label("User ID: "); 
	private TextBox userIDTextbox = new TextBox();
	
	private Label userNameLabel = new Label("Name: ");
	private TextBox userNameTextbox = new TextBox();

	private Label userPasswordLabel = new Label("Password: ");
	private PasswordTextBox userPasswordTextBox = new PasswordTextBox();
	private Label userPasswordConfirmLabel = new Label("Confirm Password: ");
	private PasswordTextBox userPasswordConfirmTextBox = new PasswordTextBox();

	private Label eMailLabel = new Label("eMail: ");
	private TextBox eMailTextbox = new TextBox();

	private Label userRemindDayLabel = new Label("Remind Days: ");
	private TextBox userRemindDayTextbox = new TextBox();
	
	private RegistryServiceAsync userSvcAsynch = GWT.create(RegistryService.class);


	public AddUserWidget(final PopupPanel simplePopup) {
		super();
	//@Override
	//public void onModuleLoad() {	

		// Create table for form data.
		formFlexTable.setWidget(0, 0, userIDLabel);
		formFlexTable.setWidget(0, 1, userIDTextbox);
		formFlexTable.setWidget(1, 0, userNameLabel);
		formFlexTable.setWidget(1, 1, userNameTextbox);
		formFlexTable.setWidget(2, 0, userPasswordLabel);
		formFlexTable.setWidget(2, 1, userPasswordTextBox);

		formFlexTable.setWidget(3, 0, userPasswordConfirmLabel);
		formFlexTable.setWidget(3, 1, userPasswordConfirmTextBox);
		formFlexTable.setWidget(4, 0, eMailLabel);
		formFlexTable.setWidget(4, 1, eMailTextbox);		
		formFlexTable.setWidget(5, 0, userRemindDayLabel);
		formFlexTable.setWidget(5, 1, userRemindDayTextbox);
		
		userRemindDayTextbox.setText("0");
		userIDTextbox.setMaxLength(20);
		userPasswordTextBox.setMaxLength(20);
		userPasswordConfirmTextBox.setMaxLength(20);
		
		// create a user
		final NewUser nu = new NewUser();
		ServiceDefTarget endpoint = (ServiceDefTarget) userSvcAsynch;
		endpoint.setServiceEntryPoint(GWT.getModuleBaseURL() + "Registration");

		buttonPanel.add(formFlexTable);
		buttonPanel.add(addUserButton);
		buttonPanel.add(cancelButton);
		
		addUserButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				System.out.println("add button clicked");

				nu.setUserId(userIDTextbox.getText());
				nu.setName(userIDTextbox.getText());
				nu.setEmail(userIDTextbox.getText());
				nu.setPassword(userPasswordTextBox.getText());
				nu.setRemindDays( Integer.parseInt(userRemindDayTextbox.getText().toString()));
				
				//nu.setNewUser(result);				
				userSvcAsynch.createUser(nu, new AsyncCallback<User>() { 
							@Override
							public void onFailure(Throwable caught) {
								// TODO Auto-generated method stub
								System.out.println("not adding user " );
								caught.printStackTrace();
							}

							@Override
							public void onSuccess(User result) {
								
  							// check if all fields are filled in
								System.out.println("adding user " );
							}

						});
				}

			}

		);
		
		// Assemble Main panel. 
		//RootPanel.get().add(formFlexTable);
		//RootPanel.get().add(buttonPanel);
		
		this.add(formFlexTable);
		this.add(buttonPanel);

		// Associate the Main panel with the HTML host page.
		// RootPanel.get("addEditComponent").add(mainPanel);
		userIDTextbox.setFocus(true);  

	}

}