package edu.cmu.tsp6.server.notification;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;
import java.util.TimerTask;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import edu.cmu.tsp6.client.bo.NotifyUser;
import edu.cmu.tsp6.server.dao.UserDAO;

public class NotificationTask  extends TimerTask{
	ArrayList<NotifyUser> notificationList;
	UserDAO ud;
	
	/**
	 * the main process of sending a notification mail
	 */
	public void run(){
        try{
        	System.out.println("Task Executing--------->");
        	setList(); 		// get the user list from dao
			Iterator<NotifyUser> it = notificationList.iterator();
			while(it.hasNext()){
				NotifyUser nu = it.next();
				String mailContent = setTemplate(nu.getName(),nu.getDate());
				sendMail("Birthday Reminder Mail",nu.getEmail(), mailContent); 
				System.out.println("Sent mail to:"+nu.getEmail());
			}
        }catch(Exception ex){
        	ex.printStackTrace();
        }
    }
	/**
	 * Function to retrieve the list from User DAO 
	 */
	void setList(){
		ud = UserDAO.getInstance();
		notificationList = (ArrayList<NotifyUser>) ud.getSendUserList();
	}
	
	/**
	 * Setting mailContent using template
	 * @param name
	 * @param birthday
	 * @return
	 */
	String setTemplate(String name, Date birthday){
		String mailContent
			="<html>																													"
			+"<head>                                                                                                                  	"
			+"<title>Birthday Reminder Mail</title>                                                                                   	"
			+"</head>                                                                                                                 	"
			+"<body>                                                                                                                  	"
			+"<table cellspacing=\"0\" cellpadding=\"10\" border=\"0\" width=\"100%\">                                                	"
			+"	<tbody>                                                                                                             	"
			+"		<tr>																												"
			+"			<td valign=\"top\">                                                                                           	"
			+"			<p><font face=\"Arial, Helvetica, sans-serif\" color=\"#003366\" size=\"3\" style=\"font-size: 14px;\">       	"
			+"				<b>Birthday Reminder Mail</b></font>                                                                    	"
			+"			</p>																											"
			+"			<p><font face=\"Arial, Helvetica, sans-serif\" color=\"#515151\" size=\"2\" style=\"font-size: 12px;\">    		"
			+"				Hi,</font>                                                                                              	"
			+"			</p>																											"
			+"			<p><font face=\"Arial, Helvetica, sans-serif\" color=\"#515151\" size=\"2\" style=\"font-size: 12px;\">      	"
			+"				This is to remind you that your buddy,  "+name+"'s birthday is coming up on " +birthday.toString()+".<br/> Thank you. </font>"
			+"			</p>																											"
			+"			</td>                                                                          									"
			+"		</tr>   																											"
			+"	</tbody>                                                                                                            	"
			+"</body>                                                                                                                 	"
			+"</table>																													"
			+"</html>																													";
		
		return mailContent;
	}
	/**
	 * Actual sending of emails
	 * @param subject
	 * @param from
	 * @param fromName
	 * @param to
	 * @param content
	 * @throws Exception
	 */
	void sendMail(String subject, String to, String content) throws Exception {
    String host = "smtp.gmail.com";//smtp server
    String from = "suwopa@gmail.com";
    String fromName = "Team6";
    
    try{
        // Properties Instantiation
        Properties props = new Properties();
       
        props.put("mail.smtp.starttls.enable","true");
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host", host);
        props.setProperty("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.port", "465");
         props.put("mail.smtp.auth", "true");
       
        MyAuthenticator auth = new MyAuthenticator("suwopa@gmail.com", "test12345");
       
        Session mailSession = Session.getDefaultInstance(props, auth);
       
        Message msg = new MimeMessage(mailSession);
        msg.setFrom(new InternetAddress(from, MimeUtility.encodeText(fromName,"UTF-8","B")));//fromAddress Setting
        InternetAddress[] address = {new InternetAddress(to)};
       
        msg.setRecipients(Message.RecipientType.TO, address);//Receiver setting
       
        msg.setSubject(subject);// Subject Setting
        msg.setSentDate(new java.util.Date());// SentDate Setting
        msg.setContent(content,"text/html;"); // Setting of content  
       
        Transport.send(msg); // Sending Message
       
        System.out.println("Completed mail send request.");
    } catch ( MessagingException ex ) {
        System.out.println("mail send error : " + ex.getMessage());
    } catch ( Exception e ) {
        System.out.println("error : " + e.getMessage());
    }
}
}
