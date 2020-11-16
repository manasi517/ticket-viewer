package com.zendesk.ticketviewer.exception;

import org.springframework.http.HttpStatus;

import com.zendesk.ticketviewer.enums.ErrorCodeEnum;

public class InvalidCredentialsException extends TicketViewerException{

	public InvalidCredentialsException() {
		super();

	}
	public InvalidCredentialsException(ErrorCodeEnum errCode,HttpStatus status) {
		super(errCode,status);

	}

	public InvalidCredentialsException(String message, Throwable cause) {
		super(message, cause);

	}

	public InvalidCredentialsException(String message) {
		super(message);

	}

	public InvalidCredentialsException(Throwable cause) {
		super(cause);

	}

	
	
}
