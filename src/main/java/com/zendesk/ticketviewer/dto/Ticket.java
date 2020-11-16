package com.zendesk.ticketviewer.dto;

import java.util.Date;
/**
 * Class is used to map ticket response from Zendesk API
 * @author manasi
 *
 */
public class Ticket {

	private int id;
	private String subject;
	private String status;
	private String url;
	private String type;
	private String priority;
	private Date created_at;
	private Date updated_at;
	private String requester_id;
	private String description;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public Date getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	public Date getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}
	public String getRequester_id() {
		return requester_id;
	}
	public void setRequester_id(String requester_id) {
		this.requester_id = requester_id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return "Ticket [id=" + id + ", subject=" + subject + ", status=" + status + ", url=" + url + ", type=" + type
				+ ", priority=" + priority + ", created_at=" + created_at + ", updated_at=" + updated_at
				+ ", requester_id=" + requester_id + ", description=" + description + "]";
	}
	
	
	
}
