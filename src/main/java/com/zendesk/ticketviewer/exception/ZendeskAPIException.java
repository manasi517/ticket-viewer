package com.zendesk.ticketviewer.exception;

import org.springframework.http.HttpStatus;

import com.zendesk.ticketviewer.enums.ErrorCodeEnum;

public class ZendeskAPIException extends TicketViewerException {

	public ZendeskAPIException() {
		super();

	}

	public ZendeskAPIException(ErrorCodeEnum errCode,HttpStatus status) {
		super(errCode,status);

	}

	public ZendeskAPIException(String message, Throwable cause) {
		super(message, cause);

	}

	public ZendeskAPIException(String message) {
		super(message);

	}

	public ZendeskAPIException(Throwable cause) {
		super(cause);

	}

	
}
