package main.java.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Message implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Timestamp date_time;
	private String text;
	private Timestamp message_thread_date_time_started_fkey;
	private int person_auto_id_fkey;

	public Message(Timestamp date_time, String text, Timestamp message_thread_date_time_started_fkey,
			int person_auto_id_fkey) {
		this.date_time = date_time;
		this.text = text;
		this.message_thread_date_time_started_fkey = message_thread_date_time_started_fkey;
		this.person_auto_id_fkey = person_auto_id_fkey;
	}

	public Timestamp getDate_time() {
		return date_time;
	}

	public String getText() {
		return text;
	}

	public Timestamp getMessage_thread_date_time_started_fkey() {
		return message_thread_date_time_started_fkey;
	}

	public int getPerson_auto_id_fkey() {
		return person_auto_id_fkey;
	}
}
