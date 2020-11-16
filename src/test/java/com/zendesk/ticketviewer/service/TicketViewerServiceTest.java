package com.zendesk.ticketviewer.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.ResourceAccessException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zendesk.ticketviewer.TicketViewerApplication;
import com.zendesk.ticketviewer.constants.Constant;
import com.zendesk.ticketviewer.dto.Ticket;
import com.zendesk.ticketviewer.dto.TicketWrapper;
import com.zendesk.ticketviewer.exception.DataNotFoundException;

@RunWith(SpringRunner.class)
@RestClientTest({TicketViewerServiceImpl.class,TicketViewerApplication.class})
public class TicketViewerServiceTest 
{
	@Autowired 
	TicketViewerService ticketViewerService;
	
	@Autowired
    private MockRestServiceServer server;
	
	@Autowired
    private ObjectMapper objectMapper;

	private Ticket ticket;
	private List<Ticket> ticketList;
	private TicketWrapper wrapper;
	private String wrapperStr;
	private String ticketUrl = "/com.zendesk.api.dummyUrl";

	@Before 
	public void setup() throws JsonProcessingException {

		ticket = new Ticket();
		ticket.setId(1);
		ticket.setPriority("High");
		ticket.setStatus("Open");
		ticket.setUrl(ticketUrl);
		
		ticketList = new ArrayList<>();
		ticketList.add(ticket);
		
		wrapper = new TicketWrapper();
		wrapper.setTicket(ticket);
		wrapper.setTickets(ticketList);
		
		wrapperStr = objectMapper.writeValueAsString(wrapper);  
	}

	@Test
    public void getTicketListTest() throws Exception {

		this.server.expect(requestTo(Constant.TICKET_LIST_URL))
        .andRespond(withSuccess(wrapperStr, MediaType.APPLICATION_JSON)); 
		
        TicketWrapper newWrapper = this.ticketViewerService.getTicketList(1,Constant.TICKET_LIST_URL);
        
        assertEquals(ticketList.size(), newWrapper.getTickets().size());
    }
	
	@Test(expected = DataNotFoundException.class)
    public void getTicketListNotFoundTest() throws Exception {

		this.server.expect(requestTo(Constant.TICKET_LIST_URL))
        .andRespond(withSuccess("", MediaType.APPLICATION_JSON)); 
		
		this.ticketViewerService.getTicketList(1,Constant.TICKET_LIST_URL);
    }
	
	@Test
    public void getTicketDetailsTest() throws Exception {

		this.server.expect(requestTo(ticketUrl))
        .andRespond(withSuccess(wrapperStr, MediaType.APPLICATION_JSON)); 
		
        Ticket newTicket = this.ticketViewerService.getTicketDetails(ticketUrl, 1);
        
        assertEquals(ticket.getId(), newTicket.getId());
        assertEquals(ticket.getPriority(), newTicket.getPriority());
        assertEquals(ticket.getStatus(), newTicket.getStatus());
    }
	
	@Test(expected = DataNotFoundException.class)
    public void getTicketDetailsNotFoundTest() throws Exception {

		wrapper.setTicket(null);
		wrapperStr = objectMapper.writeValueAsString(wrapper);  
		
		this.server.expect(requestTo(ticketUrl))
        .andRespond(withSuccess(wrapperStr, MediaType.APPLICATION_JSON)); 
		
        this.ticketViewerService.getTicketDetails(ticketUrl, 1);
    }
	
	@Test(expected = ResourceAccessException.class)
    public void serviceUnavailableTest() throws Exception {

		this.server.expect(requestTo(ticketUrl))
        .andRespond(withStatus(HttpStatus.SERVICE_UNAVAILABLE)); 
        this.ticketViewerService.getTicketDetails(ticketUrl, 1);
    }
}
