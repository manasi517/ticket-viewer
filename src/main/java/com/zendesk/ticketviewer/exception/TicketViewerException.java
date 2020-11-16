package com.zendesk.ticketviewer.exception;

import java.io.IOException;

import org.springframework.http.HttpStatus;

import com.zendesk.ticketviewer.enums.ErrorCodeEnum;

public class TicketViewerException extends IOException{

	private ErrorCodeEnum err;
	private HttpStatus status;
	
	public TicketViewerException(ErrorCodeEnum errCode,HttpStatus status) {
		super(errCode.getErrorDesc());
		this.err = errCode;
		this.status = status;
	}

	public TicketViewerException() {
		super();

	}

	public TicketViewerException(String message, Throwable cause) {
		super(message, cause);

	}

	public TicketViewerException(String message) {
		super(message);

	}

	public TicketViewerException(Throwable cause) {
		super(cause);

	}

	public ErrorCodeEnum getErr() {
		return err;
	}

	public void setErr(ErrorCodeEnum err) {
		this.err = err;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	

}
