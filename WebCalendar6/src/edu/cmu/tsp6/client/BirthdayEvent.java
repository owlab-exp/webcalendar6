package edu.cmu.tsp6.client;

import java.util.Calendar;
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
	
	/** The birthday's person */
	private String birthdayPerson;

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getBirthdayPerson() {
		return birthdayPerson;
	}

	public void setBirthdayPerson(String birthdayPerson) {
		this.birthdayPerson = birthdayPerson;
	}
	
		
}
