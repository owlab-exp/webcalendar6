/**
 * 
 */
package edu.cmu.tsp6.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

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
	
	private Label messageTextLabel = new Label();

	private final LoginServiceAsync loginService = GWT.create(LoginService.class);

	/* (non-Javadoc)
	 * @see com.google.gwt.core.client.EntryPoint#onModuleLoad()
	 */
	@Override
	public void onModuleLoad() {
		// TODO Auto-generated method stub
		
		userIdTextBox.setMaxLength(20);
		userPasswordTextBox.setMaxLength(20);
		loginPanel.add(userIdLabel);
		loginPanel.add(userIdTextBox);
		loginPanel.add(userPasswordLabel);
		loginPanel.add(userPasswordTextBox);
		loginPanel.add(loginSubmitButton);
		loginPanel.add(registerUserLink);
		
		loginPanel.add(messageTextLabel);
		
		RootPanel.get("main").add(loginPanel);
		
		userIdTextBox.setFocus(true);
		
		loginSubmitButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event){
				System.out.println("addClickHandler");
				sendAuthInfoToServer();
			}
		});
		registerUserLink.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event){
				
			}
		});
	}
	
	private void sendAuthInfoToServer() {
		loginSubmitButton.setEnabled(false);
		String id = userIdTextBox.getText();
		String password = userPasswordTextBox.getText();
		
		System.out.println("ID: " + id + ", PW: " + password);
		
		try {
			loginService.loginServer(id, password, new AsyncCallback<String>(){
				public void onFailure(Throwable caught) {
					messageTextLabel.setText("Login failure: " + caught.getMessage());
					loginSubmitButton.setEnabled(true);
					userIdTextBox.setFocus(true);

				}
				public void onSuccess(String result) {
					messageTextLabel.setText("Welcome " + result);
					loginSubmitButton.setEnabled(true);
					// Invisible after logged in
					//loginPanel.setVisible(false);
					loginPanel.removeFromParent();
					
				}
			});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
