package edu.cmu.tsp6.client.bo;
/**
 * This class represent user information
 * in the web calendar system.
 * 
 * @author YONG
 *
 */
public class User {
	private String userId;
	private String password;
	private String name;
	private String email;
	private int remindDays;
	
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof User)) {
			return false;
		}
		User other = (User)obj;
		return this.userId.equals(other.userId);
	}
	public int getRemindDays() {
		return remindDays;
	}
	public void setRemindDays(int remindDays) {
		this.remindDays = remindDays;
	}
	public String getEmail() {
		/**
		 * retrieve e-mail data for user
		 */
		return email;
	}
	public String getName() {
		/**
		 * retrieve name data for user
		 */
		return name;
	}
	public String getPassword() {
		/**
		 * retrieve password data for user
		 */
		return password;
	}
	public String getUserId() {
		/**
		 * retrieve user id data for user
		 */
		return userId;
	}
	public void setEmail(String string) {
		/**
		 * set email data for user
		 */
		email = string;
	}
	public void setName(String string) {
		/**
		 * set name data for user
		 */
		name = string;
	}
	public void setPassword(String string) {
		/**
		 * set password data for user
		 */
		password = string;
	}
	public void setUserId(String string) {
		/**
		 * set user id data for user
		 */
		userId = string;
	} 
	
}
