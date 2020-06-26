package main.java.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashMap;

public class MessageThread implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Timestamp date_time_started;
	private String title;
	private String event_title_fkey;
	private HashMap<Timestamp, Message> messages;
	
	public MessageThread(Timestamp date_time_started, String title, String event_title_fkey) {
		this.date_time_started = date_time_started;
		this.title = title;
		this.event_title_fkey = event_title_fkey;
	}

	public Timestamp getDate_time_started() {
		return date_time_started;
	}

	public String getTitle() {
		return title;
	}

	public String getEvent_title_fkey() {
		return event_title_fkey;
	}

	public HashMap<Timestamp, Message> getMessages() {
		return messages;
	}

	public void setMessages(HashMap<Timestamp, Message> messages) {
		this.messages = messages;
	}	
}
