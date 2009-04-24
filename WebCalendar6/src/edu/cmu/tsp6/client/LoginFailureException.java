package edu.cmu.tsp6.client;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.SerializationException;

public class LoginFailureException extends SerializationException implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public LoginFailureException(){
	}
	public LoginFailureException(String message) {
		super(message);
	}

}
