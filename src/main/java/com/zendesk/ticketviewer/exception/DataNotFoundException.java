package com.zendesk.ticketviewer.exception;

import org.springframework.http.HttpStatus;

import com.zendesk.ticketviewer.enums.ErrorCodeEnum;

public class DataNotFoundException extends TicketViewerException {

	
	public DataNotFoundException() {
		super();
	}
	
	public DataNotFoundException(ErrorCodeEnum err,HttpStatus status) {
		super(err,status);
	}

	public DataNotFoundException(String message, Throwable cause) {
		super(message, cause);

	}

	public DataNotFoundException(String message) {
		super(message);

	}

	public DataNotFoundException(Throwable cause) {
		super(cause);

	}
}
