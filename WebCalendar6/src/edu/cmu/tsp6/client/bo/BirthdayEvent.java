package edu.cmu.tsp6.client.bo;



/** 
 * This class represents a birthday event. Birthdays
 * are recorded regardless of year of birth of the 
 * birthday person
 * 
 * @author Non
 *
 */
public class BirthdayEvent extends Event {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8863804626182930952L;
	User birthdayPerson;

	public User getBirthdayPerson() {
		return birthdayPerson;
	}

	public void setBirthdayPerson(User birthdayPerson) {
		this.birthdayPerson = birthdayPerson;
	}
	
		
}
