/**
 * 
 */
package edu.cmu.tsp6.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.InvocationException;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;




/**
 * @author Ernest Lee
 *
 */
public class LoginEntry implements EntryPoint {

	private VerticalPanel loginPanel = new VerticalPanel();
	private TextBox userIdTextBox = new TextBox();
	private PasswordTextBox userPasswordTextBox = new PasswordTextBox();
	
	private Label userIdLabel = new Label("User ID");
	private Label userPasswordLabel = new Label("Password");
	
	private Button loginSubmitButton = new Button("Submit");
	
	private Hyperlink registerUserLink = new Hyperlink("Register User", "foo");
	
	//private Hyperlink removeEventLink = new Hyperlink("[Test]Remove Event", "foo");
	
	private Label messageTextLabel = new Label();

	private final LoginServiceAsync loginService = GWT.create(LoginService.class);

	private RootPanel mainPanel = null;
	private RootPanel listPanel = null;
	/* (non-Javadoc)
	 * @see com.google.gwt.core.client.EntryPoint#onModuleLoad()
	 */
	@Override
	public void onModuleLoad() {
		// TODO Auto-generated method stub
		
		//loginPanel.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);
		userIdTextBox.setMaxLength(20);
		userPasswordTextBox.setMaxLength(20);
		loginPanel.add(userIdLabel);
		loginPanel.add(userIdTextBox);
		loginPanel.add(userPasswordLabel);
		loginPanel.add(userPasswordTextBox);
		loginPanel.add(loginSubmitButton);
		loginPanel.add(registerUserLink);
		
		//loginPanel.add(removeEventLink);
		
		loginPanel.add(messageTextLabel);
		
		
		// attach this panel to ...
		RootPanel.get("login").add(loginPanel);
		
		Globals.putPanel("loginPanel", loginPanel);
		
		mainPanel = RootPanel.get("main");
		listPanel = RootPanel.get("list");
		mainPanel.setVisible(false);
		listPanel.setVisible(false);

		userIdTextBox.setFocus(true);
		
		loginSubmitButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event){
				System.out.println("addClickHandler");
				sendAuthInfoToServer();
			}
		});
		registerUserLink.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event){
				 PopupPanel profilePopup = new PopupPanel(true);
			     //simplePopup.setWidget(new HTML("EditProfileCommand"));
			     profilePopup.setWidget(new AddUserWidget(profilePopup));
			     profilePopup.show();
			}
		});
		
//		removeEventLink.addClickHandler(new ClickHandler() {
//			public void onClick(ClickEvent event){
//				 PopupPanel eventRemovePopup = new PopupPanel(true);
//			     //simplePopup.setWidget(new HTML("EditProfileCommand"));
//				 RemoveEventWidget removeEventWidget = new RemoveEventWidget(eventRemovePopup);
//				 
//				 removeEventWidget.setEventId(new Integer(1));
//				 
//			     eventRemovePopup.setWidget(removeEventWidget);
//			     eventRemovePopup.show();
//			}
//		});
		
	}
	
	private void sendAuthInfoToServer() {
		loginSubmitButton.setEnabled(false);
		String id = userIdTextBox.getText();
		String password = userPasswordTextBox.getText();
		
		System.out.println("ID: " + id + ", PW: " + password);
		
		try {
			loginService.loginServer(id, password, new AsyncCallback<String>(){
				public void onFailure(Throwable caught) {
					
//					messageTextLabel.setText("Login failure: " + caught.getClass().getName());
//					loginSubmitButton.setEnabled(true);
//					userIdTextBox.setFocus(true);
					try {
						throw caught;
					} catch(InvocationException ie) {
						messageTextLabel.setText("Internal error occurred");
						loginSubmitButton.setEnabled(true);
					} catch(LoginFailureException lfe) {
						messageTextLabel.setText("Login failed: " + lfe.getMessage());
						loginSubmitButton.setEnabled(true);
					} catch(Throwable t) {
						messageTextLabel.setText("Unexpected error occurred");
						loginSubmitButton.setEnabled(true);
					}

				}
				public void onSuccess(String result) {
					//message has to be sent to another message field
					messageTextLabel.setText("");
					loginSubmitButton.setEnabled(true);
					userIdTextBox.setText("");
					userPasswordTextBox.setText("");
					// Invisible after logged in
					loginPanel.setVisible(false);
					//loginPanel.removeFromParent();
					mainPanel.setVisible(true);
					listPanel.setVisible(true);
					
				}
			});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
