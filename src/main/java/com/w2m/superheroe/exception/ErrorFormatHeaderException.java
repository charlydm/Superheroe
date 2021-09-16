package com.w2m.superheroe.exception;

public class ErrorFormatHeaderException extends BadRequestException {
	
	private static final long serialVersionUID = 1L;

	public ErrorFormatHeaderException(String detail) {
		super(detail);
	}

}
