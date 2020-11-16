package com.zendesk.ticketviewer.service;

import com.zendesk.ticketviewer.dto.Ticket;
import com.zendesk.ticketviewer.dto.TicketWrapper;
import com.zendesk.ticketviewer.exception.TicketViewerException;

public interface TicketViewerService {

	/**
	 * Method is used to get the ticket list associated with account
	 * @param page - page number in ticket list
	 * @param url 
	 * @return list of tickets
	 * @throws TicketViewerException 
	 */
	public TicketWrapper getTicketList(int page, String url) throws TicketViewerException;

	/**
	 * Method is used to get ticket details for particular ticket
	 * @param url associated with ticket id
	 * @return ticket details
	 * @throws TicketViewerException 
	 */
	Ticket getTicketDetails(String url, int id) throws TicketViewerException;

}
