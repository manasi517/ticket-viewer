package com.zendesk.ticketviewer.service;

import java.nio.charset.Charset;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.zendesk.ticketviewer.constants.Constant;
import com.zendesk.ticketviewer.dto.Ticket;
import com.zendesk.ticketviewer.dto.TicketWrapper;
import com.zendesk.ticketviewer.enums.ErrorCodeEnum;
import com.zendesk.ticketviewer.exception.DataNotFoundException;
import com.zendesk.ticketviewer.exception.TicketViewerException;
import com.zendesk.ticketviewer.exception.handler.RestTemplateResponseErrorHandler;

@Service
public class TicketViewerServiceImpl implements TicketViewerService {

	private static Logger logger = LoggerFactory.getLogger(TicketViewerService.class);

	RestTemplate restTemplate;
	
	@Autowired
	public TicketViewerServiceImpl(RestTemplateBuilder restTemplateBuilder) {
		restTemplate = restTemplateBuilder
				.errorHandler(new RestTemplateResponseErrorHandler())
				.build();
	}

	/**
	 * Method is used to get the ticket list associated with account
	 * @param page - page number in ticket list
	 * @param pageSize - number of tickets to display in one page
	 * @return list of tickets
	 * @throws TicketViewerException  
	 */
	@Override
	public TicketWrapper getTicketList(int page, String url) throws TicketViewerException 
	{
		logger.info("Fetching ticket list for page {} ",page);
		TicketWrapper ticketWrapper;
		if(null == url)
			ticketWrapper = callZendeskApi(Constant.TICKET_LIST_URL);
		else 
			ticketWrapper = callZendeskApi(url);
		
		if(null == ticketWrapper){
			logger.error("No tickets available for url");
			throw new DataNotFoundException(ErrorCodeEnum.TV003,HttpStatus.NOT_FOUND);
		}
		return ticketWrapper;
	}
	

	/**
	 * Method is used to get ticket details for particular ticket
	 * @param url associated with ticket id
	 * @return ticket details
	 * @throws TicketViewerException 
	 */
	@Override
	public Ticket getTicketDetails(String url, int id) throws TicketViewerException 
	{
		logger.info("Fetching ticket details with id {}",id);
		Ticket ticket = null;
		TicketWrapper ticketWrapper = callZendeskApi(url);

		if(null != ticketWrapper)
			ticket = ticketWrapper.getTicket();

		if(null == ticket) {
			logger.error("Ticket not found for id: {} and url: {}",id,url);
			throw new DataNotFoundException(ErrorCodeEnum.TV003,HttpStatus.NOT_FOUND);
		}
		logger.info("Fetched ticket details: {}",ticket);
		return ticket;
	}
	
	/**
	 * Method is used to call zendesk API to get ticket list or ticket details based on provided url
	 * @param url for API
	 * @return wrapper class object which contains ticket list or ticket details
	 * @throws TicketViewerException 
	 */
	private TicketWrapper callZendeskApi(String url) throws TicketViewerException  
	{
		TicketWrapper wrapper = null;
		try {
			ResponseEntity<TicketWrapper> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<TicketWrapper>(
					createHeaders(Constant.EMAIL, Constant.PASSWORD)), TicketWrapper.class);
			if(null != response)
				wrapper = response.getBody();
		}
		catch (IllegalArgumentException ex) {
			logger.error("Invalid API Url for ZendeskAPI ",ex);
			throw new TicketViewerException(ErrorCodeEnum.TV001,HttpStatus.BAD_REQUEST);
		}
		return wrapper;
	}

	/**
	 * Method is used to set the credentials using Basic Auth encoding for zendesk API 
	 * @param username
	 * @param password
	 * @return http header required for API with encoded credentials
	 */
	private HttpHeaders createHeaders(String username, String password){
		return new HttpHeaders() {{
			String auth = username + ":" + password;
			byte[] encodedAuth = Base64.encodeBase64( 
					auth.getBytes(Charset.forName("US-ASCII")) );
			String authHeader = "Basic " + new String( encodedAuth );
			set( "Authorization", authHeader );
		}};
	}
}
