package com.zendesk.ticketviewer.controller;

import java.sql.Timestamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.zendesk.ticketviewer.constants.Constant;
import com.zendesk.ticketviewer.dto.Ticket;
import com.zendesk.ticketviewer.dto.TicketWrapper;
import com.zendesk.ticketviewer.exception.ErrorResponse;
import com.zendesk.ticketviewer.exception.TicketViewerException;
import com.zendesk.ticketviewer.service.TicketViewerService;

@RestController
public class TicketViewerController {

	private static Logger logger = LoggerFactory.getLogger(TicketViewerController.class);

	@Autowired
	TicketViewerService ticketViewerService;

	/**
	 * Method is used to get the ticket list associated with account
	 * @param page - page number in ticket list
	 * @param pageSize - number of tickets to display in one page
	 * @return list of tickets
	 * @throws TicketViewerException 
	 */
	@GetMapping("/tickets")
	public ModelAndView getTicketList(@RequestParam(required = false,defaultValue = "1") int page, 
			@RequestParam(required = false) String url) throws TicketViewerException  {

		logger.info("Request for ticket list with page {} ",page);
		TicketWrapper restaurants = ticketViewerService.getTicketList(page, url);

		ModelAndView mav = new ModelAndView(Constant.TICKET_LIST_PAGE);
		mav.addObject(Constant.WRAPPER_OBJ, restaurants);
		mav.addObject(Constant.PAGE_NO_OBJ, page);
		return mav;
	}

	/**
	 * Method is used to get ticket details for particular ticket
	 * @param url associated with ticket id
	 * @return ticket details
	 * @throws TicketViewerException 
	 */
	@GetMapping("/ticketDetails")
	public ModelAndView getTicketDetails(@RequestParam int id, @RequestParam String url) throws TicketViewerException {

		logger.info("request for ticket details with ticketId {}",id);
		Ticket restaurants = ticketViewerService.getTicketDetails(url, id);	

		ModelAndView mav = new ModelAndView(Constant.TICKET_DETAILS_PAGE);
		mav.addObject(Constant.TICKET_OBJ, restaurants);
		return mav;
	}

	/**
	 * Exception handler for TicketViewer application custom exceptions
	 * @param ticketViewerException
	 */
	@ExceptionHandler(value=TicketViewerException.class)
	public ModelAndView ticketviewerExceptionHandler(TicketViewerException ticketViewerException){

		ErrorResponse errorResponse = new ErrorResponse(ticketViewerException.getErr().getErrorCode(), 
				ticketViewerException.getErr().getErrorDesc(), new Timestamp(System.currentTimeMillis()));

		ModelAndView mav = new ModelAndView(Constant.ERROR);
		mav.addObject(Constant.ERROR, errorResponse);
		mav.setStatus(ticketViewerException.getStatus());
		return mav;
	}
}
