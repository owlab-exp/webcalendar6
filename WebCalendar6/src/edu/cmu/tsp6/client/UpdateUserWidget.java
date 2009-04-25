package edu.cmu.tsp6.client;
 
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler; 
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel; 
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.VerticalPanel; 

import edu.cmu.tsp6.client.bo.User;
import edu.cmu.tsp6.client.bo.NewUser;

public class UpdateUserWidget extends VerticalPanel {

	private FlexTable formFlexTable = new FlexTable();
	private HorizontalPanel buttonPanel = new HorizontalPanel();
	private Button addUserButton = new Button("Update");
	private Button cancelButton = new Button("Cancel");
	
	// labels and textboxes
	private Label userIDLabel = new Label("User ID: "); 
	private TextBox userIDTextbox = new TextBox();
	
	private Label userNameLabel = new Label("Name: ");
	private TextBox userNameTextbox = new TextBox();

	private Label userPasswordLabel = new Label("Password: ");
	private TextBox userPasswordTextBox = new PasswordTextBox();
	private Label userPasswordConfirmLabel = new Label("Confirm Password: ");
	private TextBox userPasswordConfirmTextBox = new PasswordTextBox();

	private Label eMailLabel = new Label("eMail: ");
	private TextBox eMailTextbox = new TextBox();

	private Label userRemindDayLabel = new Label("Remind Days: ");
	private TextBox userRemindDayTextbox = new TextBox();
	
	private UpdateServiceAsync userSvcAsynch = GWT.create(UpdateService.class);

	public UpdateUserWidget(final PopupPanel simplePopup) {
		super();
				
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
		 
		//HttpSession session = null;
		int left = (Window.getClientWidth() - 100) / 3;
        int top = (Window.getClientHeight() - 100) / 3;
        
        simplePopup.setPopupPosition(left, top);
        
		// create a user
		final NewUser nu = new NewUser();
		ServiceDefTarget endpoint = (ServiceDefTarget) userSvcAsynch;
		endpoint.setServiceEntryPoint(GWT.getModuleBaseURL() + "Profile");

		buttonPanel.add(addUserButton);
		buttonPanel.add(cancelButton);		
		userIDTextbox.setEnabled(false);
		
		userSvcAsynch.getCurrentUser(new AsyncCallback<User>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onSuccess(User result) {
				// fake user code
				if (result == null) {
					result = new User();
					result.setUserId("coconut");
					result.setName("Cocos");
				}
				//nu.setNewUser(result) ;
		
				userIDTextbox.setText(result.getUserId() );
				userNameTextbox.setText(result.getName() );
				eMailTextbox.setText(result.getEmail());
				userPasswordTextBox.setText(result.getPassword());
				userPasswordConfirmTextBox.setText(result.getPassword());				 
				userRemindDayTextbox.setText(String.valueOf(result.getRemindDays()));
			}
		});
		
		addUserButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				System.out.println("add button clicked");
				
				nu.setUserId(userIDTextbox.getText());
				nu.setName(userNameTextbox.getText());
				nu.setEmail(eMailTextbox.getText());
				nu.setPassword(userPasswordTextBox.getText());
				nu.setRemindDays( Integer.parseInt(userRemindDayTextbox.getText()));
	
				//nu.setNewUser(result);				
				userSvcAsynch.updateUser(nu, new AsyncCallback<User>() { 
					@Override
					public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
						System.out.println("not adding user " );
					}

					@Override
					public void onSuccess(User result) {
					
						System.out.println("user updated" );						

						simplePopup.setVisible(false);		
					}
				});}
			}
		);			
		
		cancelButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				System.out.println("cancel button clicked");

				userIDTextbox.setText("");
				userNameTextbox.setText("");
				eMailTextbox.setText("");
				userPasswordTextBox.setText("");
				userRemindDayTextbox.setText("0");
				userPasswordConfirmTextBox.setText("");

				simplePopup.setVisible(false);
				}
			}
		);			

			// Assemble Main panel.
			// this.add(new HTML("<title>Add Birthday Event</title>"));
			this.add(formFlexTable);
			this.add(buttonPanel);
	
			// Associate the Main panel with the HTML host page.
			// RootPanel.get("addEditComponent").add(mainPanel);
			userIDTextbox.setFocus(true);   
	
			}

}