package edu.cmu.tsp6.bo;

import java.util.Date;


/** 
 * This class represents a birthday event. Birthdays
 * are recorded regardless of year of birth of the 
 * birthday person
 * 
 * @author Non
 *
 */
public class BirthdayEvent extends Event {
	User birthdayPerson;

	public User getBirthdayPerson() {
		return birthdayPerson;
	}

	public void setBirthdayPerson(User birthdayPerson) {
		this.birthdayPerson = birthdayPerson;
	}
	
		
}
