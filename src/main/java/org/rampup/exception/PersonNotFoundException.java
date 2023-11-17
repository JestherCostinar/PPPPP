package org.rampup.exception;

@SuppressWarnings("serial")
public class PersonNotFoundException extends RuntimeException {
	public PersonNotFoundException(String message) {
		super(message);
	}
}
