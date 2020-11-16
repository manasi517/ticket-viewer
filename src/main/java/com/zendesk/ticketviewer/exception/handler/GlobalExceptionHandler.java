package com.zendesk.ticketviewer.exception.handler;

import java.sql.Timestamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.zendesk.ticketviewer.constants.Constant;
import com.zendesk.ticketviewer.enums.ErrorCodeEnum;
import com.zendesk.ticketviewer.exception.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

	private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	/**
	 * Exception handler for general exceptions 
	 * @param exception
	 */
	@ExceptionHandler(value=Exception.class) 
	public ModelAndView globalExceptionHandler(Exception exception){

		logger.error("Exception occured ",exception);
		ErrorResponse errorResponse = new ErrorResponse(ErrorCodeEnum.TV000.getErrorCode(),
				ErrorCodeEnum.TV000.getErrorDesc(), new Timestamp(System.currentTimeMillis()));

		ModelAndView mav = new ModelAndView(Constant.ERROR);
		mav.addObject(Constant.ERROR, errorResponse); 
		mav.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		return mav; 
	}
}
