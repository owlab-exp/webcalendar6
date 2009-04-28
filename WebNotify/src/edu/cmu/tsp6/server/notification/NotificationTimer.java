package edu.cmu.tsp6.server.notification;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer; 
/**
 * Timer class for sending notification
 * Implemented to accomodate both schedule task using OS and Java Timer 
 * @author spark2
 *
 */
public class NotificationTimer {
	Timer timer = new Timer();
	/**
	 * main function for execution in command line
	 * @param arg
	 */
	public static void main(String[] arg){
		NotificationTimer notificationTimer = new NotificationTimer();
		notificationTimer.batchproc();
	}
	/**
	 * This method is for execution in command line.
	 * Notification mail will be sent the moment this method is called
	 * Useful for crontab or windows scheduler execution
	 */
	public void batchproc() {
		timer = new Timer();
        NotificationTask task = new NotificationTask();
        
        //86400 
        timer.schedule(task, new Date());  
	}
	/**
	 * This method is for execution in Servlet
	 * This notification mail will be sent at 8'o clock every morning
	 */
	public void proc() {
		timer = new Timer();
        NotificationTask task = new NotificationTask();

        //Get the Date corresponding to 08:00:00 am today.
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 8);
        calendar.set(Calendar.MINUTE, 00);
        calendar.set(Calendar.SECOND, 0);
        Date time = calendar.getTime();
        java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat ("HH:mm:ss", java.util.Locale.US);
        System.out.println("Added to Queue:"+formatter.format(time));
        
        //86400 
        timer.scheduleAtFixedRate(task, time, 86400000);  
	}
	void cancel() {
		if (timer != null) {
			timer.cancel();
		}
	}

}
