package edu.cmu.tsp6.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import edu.cmu.tsp6.client.composite.CalendarWidget;

public class RemoveEventWidget extends VerticalPanel {

	// Value to be used
	private Integer eventId = null;
	private Widget wi = null;
	// UI element
	private FlexTable formFlexTable = new FlexTable();
	private HorizontalPanel buttonPanel = new HorizontalPanel();
	private Label messageTextLabel = new Label();
	private Button removeEventButton = new Button("Remove this event");
	private Button cancelButton = new Button("Cancel");

	private final RemoveEventServiceAsync removeEventService = GWT
			.create(RemoveEventService.class);

	// private RemoveEventServiceAsync removeEventService =
	// GWT.create(RemoveEventService.class);

	public RemoveEventWidget(final PopupPanel simplePopup, final Widget w, final CalendarWidget c) {
		super();

		formFlexTable.setWidget(0, 0, removeEventButton);
		formFlexTable.setWidget(0, 1, cancelButton);

		messageTextLabel.setText("");

		int callerPositionLeft = w.getAbsoluteLeft();
		int callerPositionTop = w.getAbsoluteTop();
		
//		int left = (Window.getClientWidth() - 100) / 2;
//		int top = (Window.getClientHeight() - 100) / 2;
		int left = callerPositionLeft;
		int top = callerPositionTop;

		simplePopup.setPopupPosition(left, top);
		// simplePopup.setPopupPosition(20, 30);

		buttonPanel.add(formFlexTable);

		buttonPanel.add(messageTextLabel);

		removeEventButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				System.out.println("Remove Button clicked.");

				try {
					removeEventService.removeEventServer(eventId,
							new AsyncCallback<String>() {
								public void onFailure(Throwable caught) {

									// messageTextLabel.setText("Login failure: "
									// + caught.getClass().getName());
									// loginSubmitButton.setEnabled(true);
									// userIdTextBox.setFocus(true);
									try {
										throw caught;
										// } catch(InvocationException ie) {
										// messageTextLabel.setText("Internal error occurred");
										// loginSubmitButton.setEnabled(true);
										// } catch(LoginFailureException lfe) {
										// messageTextLabel.setText("Login failed: "
										// + lfe.getMessage());
										// loginSubmitButton.setEnabled(true);
									} catch (Throwable t) {
										messageTextLabel
												.setText("Unexpected error occurred");
										// loginSubmitButton.setEnabled(true);
									}

								}

								public void onSuccess(String result) {
									// message has to be sent to another message
									// field
									messageTextLabel.setText("Remove Success ");
									// loginSubmitButton.setEnabled(true);
									// // Invisible after logged in
									// loginPanel.setVisible(false);
									// //loginPanel.removeFromParent();
									// mainPanel.setVisible(true);
									w.removeFromParent();
									c.init();
									removeEventButton.removeFromParent();
									cancelButton.setText("Close");

								}
							});
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		cancelButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				System.out.println("Cancel button clicked");
				messageTextLabel.setText("");

				simplePopup.setVisible(false);
			}

		}

		);
		this.add(formFlexTable);
		this.add(buttonPanel);

		// userIDTextbox.setFocus(true);

	}

	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}

	public void setCallerWidget(Widget w) {
		this.wi = wi;
	}
}
