package com.zendesk.ticketviewer.enums;

/**
 * Class is used to declare the error codes and desc for custom exceptions
 * @author manasi
 *
 */
public enum ErrorCodeEnum {

	TV000("TV000","Error occured in ticketviewer service"),
	TV001("TV001","Error in ZendeskAPI url"),
	TV002("TV002","Error while connecting ZendeskAPI"),
	TV003("TV003","Data not found "),
	TV004("TV004","Invalid username or password"),
	TV005("TV005","Service is temporarily unavailable"),
	TV006("TV006","Something went wrong with ZendeskAPI");
	
	private String errorCode;
	private String errorDesc;
	
	private ErrorCodeEnum(String errorCode, String errorDesc) {
		this.errorCode = errorCode;
		this.errorDesc = errorDesc;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getErrorDesc() {
		return errorDesc;
	}
	
	
}
