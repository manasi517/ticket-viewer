package com.zendesk.ticketviewer.dto;

import java.util.List;

/**
 * Class is used as wrapper to map response from Zendesk API
 * @author manasi
 */
public class TicketWrapper {

	/**
	 * Used for ticket list API
	 */
	private List<Ticket> tickets;
	private String next_page;
	private String previous_page;
	private int count;
	
	/**
	 * Used for ticket details API
	 */
	private Ticket ticket;
	
	/**
	 * Used for error from API
	 */
	private String error;
	private String description;
	
	public List<Ticket> getTickets() {
		return tickets;
	}
	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}
	public String getNext_page() {
		return next_page;
	}
	public void setNext_page(String next_page) {
		this.next_page = next_page;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Ticket getTicket() {
		return ticket;
	}
	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPrevious_page() {
		return previous_page;
	}
	public void setPrevious_page(String previous_page) {
		this.previous_page = previous_page;
	}
	@Override
	public String toString() {
		return "TicketWrapper [tickets=" + tickets + ", next_page=" + next_page + ", previous_page=" + previous_page
				+ ", count=" + count + ", ticket=" + ticket + ", error=" + error + ", description=" + description + "]";
	}
	
	
}
