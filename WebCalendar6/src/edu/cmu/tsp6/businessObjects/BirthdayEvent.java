package edu.cmu.tsp6.businessObjects;

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
	/** Date of birth */
	private Date birthdate;
	User birthdayPerson;
	/** The birthday's person */

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public User getBirthdayPerson() {
		return birthdayPerson;
	}

	public void setBirthdayPerson(User birthdayPerson) {
		this.birthdayPerson = birthdayPerson;
	}
	
		
}
