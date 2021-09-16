package com.w2m.superheroe.exception;

public class BadRequestException extends RuntimeException {
	
	private static final long serialVersionUID = 5364410082138327175L;
	
	private static final String DESCRIPTION = " Bad Request Exception (400)";

	public BadRequestException(String detail) {
		super(DESCRIPTION + " - " + detail);
	}

}