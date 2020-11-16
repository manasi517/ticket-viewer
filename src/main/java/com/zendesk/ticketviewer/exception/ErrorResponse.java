package com.zendesk.ticketviewer.exception;

import java.sql.Timestamp;

import com.zendesk.ticketviewer.enums.ErrorCodeEnum;

public class ErrorResponse {

	private String errCode;
	private String errDesc;
	private Timestamp timestamp;

	public ErrorResponse(String errCode, String errDesc, Timestamp timestamp) {
		super();
		this.errCode = errCode;
		this.errDesc = errDesc;
		this.timestamp = timestamp;
	}
	
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	public String getErrCode() {
		return errCode;
	}
	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}
	public String getErrDesc() {
		return errDesc;
	}
	public void setErrDesc(String errDesc) {
		this.errDesc = errDesc;
	}
	
}
