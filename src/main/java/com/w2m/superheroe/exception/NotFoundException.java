package com.w2m.superheroe.exception;

public class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = -119163969241070249L;

	private static final String DESCRIPTION = "Not Fount Exception: ";

	public NotFoundException(String detail) {
		super(getDescription(detail));
	}
	
	public static String getDescription(String detail) {
		StringBuilder description = new StringBuilder();
		description.append(DESCRIPTION).append(detail);
		return description.toString();
	}

}
