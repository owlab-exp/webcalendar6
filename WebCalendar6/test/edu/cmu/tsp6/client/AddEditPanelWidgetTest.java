package edu.cmu.tsp6.client;

import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.ui.PopupPanel;

/**
 * GWT JUnit tests must extend GWTTestCase.
 */
public class AddEditPanelWidgetTest extends GWTTestCase {                       

  /**
   * Must refer to a valid module that sources this class.
   */
  public String getModuleName() {                                         
    return "edu.cmu.tsp6.WebCalendar6";
  }

  /**
   * Add as many tests as you like.
   */
  public void testAddEditPanelNotNull() {
	  PopupPanel simplePopup = new PopupPanel(true);
	  
		simplePopup.setTitle("Add Event");
		AddEditPanelWidget ae = new AddEditPanelWidget(simplePopup, null);
		simplePopup.setWidget(ae);
		assertNotNull(ae);
		assertTrue(ae.ownerNameValue.getText().equals("Cocos"));
		
  }

}