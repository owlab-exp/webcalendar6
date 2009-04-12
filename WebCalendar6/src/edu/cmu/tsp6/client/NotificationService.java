/**
 * @author Sean Park
 * Desc: Notification
 */
package edu.cmu.tsp6.client;

import java.util.ArrayList;
import java.util.List;

import edu.cmu.tsp6.businessObjects.User;

public class NotificationService {
	
	ArrayList<User> notificationList;
	String mailTemplateContent;
	
	
	/**
	 * Method to fetch the list of the notification mailing list
	 */
	List<User> getList(){
		return notificationList;
	}
	/**
	 * Method to fetch the content template for mailing
	 */
	void getMailTemplate() {
		
	}
	
	/**
	 * notifyuser method.  Entry point for sending email 
	 * @param userID String
	 */
	void notifyUser() {
		
	}
	
	
}
