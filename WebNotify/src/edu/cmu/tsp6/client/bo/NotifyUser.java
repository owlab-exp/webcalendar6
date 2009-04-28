package edu.cmu.tsp6.client.bo;


import java.io.Serializable;
import java.util.Date;

/**
 * This class represent User List information for the notification mail list
 * 
 * @author Sean
 *
 */
public class NotifyUser implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4052576605376917971L;
	String userId;
	String name;
	String email;
	Date date;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}
