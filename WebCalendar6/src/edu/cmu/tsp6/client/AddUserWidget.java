package edu.cmu.tsp6.client;
 
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler; 
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel; 
 
import edu.cmu.tsp6.client.bo.User;
import edu.cmu.tsp6.client.bo.NewUser;

public class AddUserWidget extends VerticalPanel {

	private FlexTable formFlexTable = new FlexTable();
	private HorizontalPanel buttonPanel = new HorizontalPanel();
	private Button addUserButton = new Button("Add");

	// labels and textboxes
	private Label userIDLabel = new Label("User ID: "); 
	private TextBox userIDTextbox = new TextBox();
	
	private Label userNameLabel = new Label("Name: ");
	private TextBox userNameTextbox = new TextBox();

	private Label userPasswordLabel = new Label("Password: ");
	private TextBox userPasswordTextBox = new TextBox();
	private Label userPasswordConfirmLabel = new Label("Confirm Password: ");
	private TextBox userPasswordConfirmTextBox = new TextBox();

	private Label eMailLabel = new Label("eMail: ");
	private TextBox eMailTextbox = new TextBox();

	private Label userRemindDayLabel = new Label("Remind Days: ");
	private TextBox userRemindDayTextbox = new TextBox();
	
	private RegistryServiceAsync userSvcAsynch = GWT.create(RegistryService.class);

	public AddUserWidget(final PopupPanel simplePopup) {
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
		
		userIDTextbox.setReadOnly(true);
		
		userRemindDayTextbox.setText("0");

		// create a user
		final NewUser nu = new NewUser();
		ServiceDefTarget endpoint = (ServiceDefTarget) userSvcAsynch;
		endpoint.setServiceEntryPoint(GWT.getModuleBaseURL() + "users");

		buttonPanel.add(addUserButton);

		addUserButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				System.out.println("add button clicked");
				
				userSvcAsynch.createUser(nu, new AsyncCallback<User>() {

							@Override
							public void onFailure(Throwable caught) {
								// TODO Auto-generated method stub
							}

							@Override
							public void onSuccess(User result) {
								System.out.println("adding user " + result);
								
								result.setUserId(userIDTextbox.getText());
								result.setName(userIDTextbox.getText());
								result.setEmail(userIDTextbox.getText());
								result.setPassword(userIDTextbox.getText());
								result.setRemindDays( Integer.parseInt(userIDTextbox.getText().toString()));
								
								nu.setNewUser(result);
  							// check if all fields are filled in
								
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
		userIDTextbox.setFocus(true);

	}

}
