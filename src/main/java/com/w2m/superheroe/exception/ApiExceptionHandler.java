package com.w2m.superheroe.exception;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException.Conflict;
import org.springframework.web.client.HttpClientErrorException.Forbidden;
import org.springframework.web.client.HttpClientErrorException.Unauthorized;

@ControllerAdvice
public class ApiExceptionHandler {
	
	private static final Logger logger = Logger.getLogger(ApiExceptionHandler.class);
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler({NotFoundException.class})
	@ResponseBody
	public ErrorMessage notFoundRequest(HttpServletRequest request, Exception exception) {
		logger.error(request.getRequestURI() + " - " + exception);
		return new ErrorMessage(exception, request.getRequestURI());
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({ BadRequestException.class, DuplicateKeyException.class,
			HttpRequestMethodNotSupportedException.class, MethodArgumentNotValidException.class,
			MissingRequestHeaderException.class, MissingServletRequestParameterException.class,
			MethodArgumentTypeMismatchException.class, HttpMessageNotReadableException.class, EmptyResultDataAccessException.class })
	@ResponseBody
	public ErrorMessage badRequest(HttpServletRequest request, Exception exception) {
		logger.error(request.getRequestURI() + " - " + exception);
		return new ErrorMessage(exception, request.getRequestURI());
	}
	
	@ResponseStatus(HttpStatus.FORBIDDEN)
	@ExceptionHandler({Forbidden.class})
	@ResponseBody
	public ErrorMessage forbiddenRequest(HttpServletRequest request, Exception exception) {
		logger.error(request.getRequestURI() + " - " + exception);
		return new ErrorMessage(exception, request.getRequestURI());
	}
	
	@ResponseStatus(HttpStatus.CONFLICT)
	@ExceptionHandler({Conflict.class})
	@ResponseBody
	public ErrorMessage conflictRequest(HttpServletRequest request, Exception exception) {
		logger.error(request.getRequestURI() + " - " + exception);
		return new ErrorMessage(exception, request.getRequestURI());
	}
	
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler({Unauthorized.class, BadCredentialsException.class})
	public void Unauthorized(HttpServletRequest request, Exception exception) {
		logger.error(request.getRequestURI() + " - " + exception);
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler({Exception.class})
	@ResponseBody
	public ErrorMessage fatalErrorUnexpectedException(HttpServletRequest request, Exception exception) {
		logger.error(request.getRequestURI() + " - " + exception);
		return new ErrorMessage(exception, request.getRequestURI());
	}

}
