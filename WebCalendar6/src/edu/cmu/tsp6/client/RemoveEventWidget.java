package edu.cmu.tsp6.client;
 
import com.google.gwt.core.client.GWT; 
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler; 
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.InvocationException;
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
import com.google.gwt.core.ext.*;
import edu.cmu.tsp6.client.bo.User;
import edu.cmu.tsp6.client.bo.NewUser;

public class RemoveEventWidget extends VerticalPanel {

	private FlexTable formFlexTable = new FlexTable();
	private HorizontalPanel buttonPanel = new HorizontalPanel();
	private Label messageTextLabel = new Label();
	private Button removeEventButton = new Button("Remove this event");
	private Button cancelButton = new Button("Cancel");
	
	private final RemoveEventServiceAsync removeEventService = GWT.create(RemoveEventService.class);

	
	//private RemoveEventServiceAsync removeEventService = GWT.create(RemoveEventService.class);

	public RemoveEventWidget(final PopupPanel simplePopup) {
		super();

		// Create table for form data.
//		formFlexTable.setWidget(0, 0, userIDLabel);
//		formFlexTable.setWidget(0, 1, userIDTextbox);
//		formFlexTable.setWidget(1, 0, userNameLabel);
//		formFlexTable.setWidget(1, 1, userNameTextbox);
//		formFlexTable.setWidget(2, 0, userPasswordLabel);
//		formFlexTable.setWidget(2, 1, userPasswordTextBox);
//
//		formFlexTable.setWidget(3, 0, userPasswordConfirmLabel);
//		formFlexTable.setWidget(3, 1, userPasswordConfirmTextBox);
//		formFlexTable.setWidget(4, 0, eMailLabel);
//		formFlexTable.setWidget(4, 1, eMailTextbox);		
//		formFlexTable.setWidget(5, 0, userRemindDayLabel);
//		formFlexTable.setWidget(5, 1, userRemindDayTextbox);

		formFlexTable.setWidget(0, 0, removeEventButton); 
		formFlexTable.setWidget(0, 1, cancelButton); 
		 
		
		messageTextLabel.setText("");
//		userRemindDayTextbox.setText("0");
//		userIDTextbox.setMaxLength(20);
//		userPasswordTextBox.setMaxLength(20);
//		userPasswordConfirmTextBox.setMaxLength(20);
		//
		 int left = (Window.getClientWidth() - 100) / 3;
         int top = (Window.getClientHeight() - 100) / 3;
         
         simplePopup.setPopupPosition(left, top);
         
		// create a user
		//final NewUser nu = new NewUser();
//		ServiceDefTarget endpoint = (ServiceDefTarget) removeEventService;
//		endpoint.setServiceEntryPoint(GWT.getModuleBaseURL() + "Registration");

		buttonPanel.add(formFlexTable);
		//buttonPanel.add(addUserButton);
		//buttonPanel.add(cancelButton);
		buttonPanel.add(messageTextLabel);
		
		//Integer eventId = new Integer("1");
		
		removeEventButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				System.out.println("Remove Button clicked.");
				
				try {
					removeEventService.removeEventServer(new Integer(1), new AsyncCallback<String>(){
						public void onFailure(Throwable caught) {
							
//						messageTextLabel.setText("Login failure: " + caught.getClass().getName());
//						loginSubmitButton.setEnabled(true);
//						userIdTextBox.setFocus(true);
							try {
								throw caught;
//						} catch(InvocationException ie) {
//							messageTextLabel.setText("Internal error occurred");
//							loginSubmitButton.setEnabled(true);
//						} catch(LoginFailureException lfe) {
//							messageTextLabel.setText("Login failed: " + lfe.getMessage());
//							loginSubmitButton.setEnabled(true);
							} catch(Throwable t) {
								messageTextLabel.setText("Unexpected error occurred");
								//loginSubmitButton.setEnabled(true);
							}

						}
						public void onSuccess(String result) {
							//message has to be sent to another message field
							messageTextLabel.setText("Remove Success ");
//						loginSubmitButton.setEnabled(true);
//						// Invisible after logged in
//						loginPanel.setVisible(false);
//						//loginPanel.removeFromParent();
//						mainPanel.setVisible(true);
							
						}
					});
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	);
		
				cancelButton.addClickHandler(new ClickHandler() {
			
				@Override
				public void onClick(ClickEvent event) {
					System.out.println("cancel button clicked");
//					userIDTextbox.setText("");
//					userNameTextbox.setText("");
//					eMailTextbox.setText("");
//					userPasswordTextBox.setText("");
//					userRemindDayTextbox.setText("0");
//					userPasswordConfirmTextBox.setText("");
					messageTextLabel.setText("");
							
					//formFlexTable.setVisible(false);
					//buttonPanel.setVisible(false);
					simplePopup.setVisible(false);
				}
			
			}

		);	
		this.add(formFlexTable);
		this.add(buttonPanel);

		//userIDTextbox.setFocus(true);  

	}

}
