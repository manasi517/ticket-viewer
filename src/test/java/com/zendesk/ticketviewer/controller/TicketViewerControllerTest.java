
package com.zendesk.ticketviewer.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.zendesk.ticketviewer.dto.Ticket;
import com.zendesk.ticketviewer.dto.TicketWrapper;
import com.zendesk.ticketviewer.enums.ErrorCodeEnum;
import com.zendesk.ticketviewer.exception.TicketViewerException;
import com.zendesk.ticketviewer.service.TicketViewerService;

@RunWith(SpringRunner.class)
@WebMvcTest({TicketViewerController.class,HomeController.class}) 
public class TicketViewerControllerTest
{

	@Autowired 
	private MockMvc mockMvc;

	@MockBean 
	TicketViewerService ticketViewerService;

	private Ticket ticket;
	private List<Ticket> ticketList;
	private TicketWrapper wrapper;

	@Before 
	public void before() {

		ticket = new Ticket();
		ticket.setId(1);
		ticket.setPriority("High");
		ticket.setStatus("Open");
		
		ticketList = new ArrayList<>();
		ticketList.add(ticket);
		
		wrapper = new TicketWrapper();
		wrapper.setTicket(ticket);
		wrapper.setTickets(ticketList);
	}

	@Test 
	public void homeControllerTest() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.get("/"))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.view().name("home"));
	}
	
	@Test 
	public void getTicketListTest() throws Exception {

		Mockito.when(ticketViewerService.getTicketList(Mockito.anyInt(), Mockito.anyString()))
				.thenReturn(wrapper);

		mockMvc.perform(MockMvcRequestBuilders.get("/tickets"))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.view().name("ticket-list"));
	}
	
	@Test 
	public void getTicketDetailsTest() throws Exception {

		Mockito.when(ticketViewerService.getTicketDetails(Mockito.anyString(), Mockito.anyInt()))
				.thenReturn(ticket);

		mockMvc.perform(MockMvcRequestBuilders.get("/ticketDetails?id=1&url=null"))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.view().name("ticket-details"));
	}
	
	@Test 
	public void serviceUnavailableTest() throws Exception  {

		Mockito.when(ticketViewerService.getTicketDetails(Mockito.anyString(), Mockito.anyInt()))
				.thenThrow(new TicketViewerException(ErrorCodeEnum.TV005,HttpStatus.SERVICE_UNAVAILABLE));

		mockMvc.perform(MockMvcRequestBuilders.get("/ticketDetails?id=1&url=null"))
		.andExpect(MockMvcResultMatchers.status().isServiceUnavailable())
		.andExpect(MockMvcResultMatchers.view().name("error"));
	}

	@Test 
	public void exceptionHandlerTest() throws Exception  {

		Mockito.when(ticketViewerService.getTicketDetails(Mockito.anyString(), Mockito.anyInt()))
				.thenThrow(new TicketViewerException(ErrorCodeEnum.TV003,HttpStatus.NOT_FOUND));

		mockMvc.perform(MockMvcRequestBuilders.get("/ticketDetails?id=1&url=null"))
		.andExpect(MockMvcResultMatchers.status().isNotFound())
		.andExpect(MockMvcResultMatchers.view().name("error"));
	}
}
