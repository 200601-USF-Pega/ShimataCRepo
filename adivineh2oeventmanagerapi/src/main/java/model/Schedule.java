package main.java.model;

import java.io.Serializable;

public class Schedule implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int schedule_auto_id;
	private int from_hour;
	private int from_minute;
	private int from_month;
	private int from_day;
	private int from_year;
	private int to_hour;
	private int to_minute;
	private int to_month;
	private int to_day;
	private int to_year;
	private boolean available;
	private int person_auto_id_fkey;
	private String event_title_fkey;

	//create schedule for person
	public Schedule(int from_hour, int from_minute, int from_month, int from_day, int from_year, int to_hour,
			int to_minute, int to_month, int to_day, int to_year, boolean available, int person_auto_id_fkey) {
		this.from_hour = from_hour;
		this.from_minute = from_minute;
		this.from_month = from_month;
		this.from_day = from_day;
		this.from_year = from_year;
		this.to_hour = to_hour;
		this.to_minute = to_minute;
		this.to_month = to_month;
		this.to_day = to_day;
		this.to_year = to_year;
		this.available = available;
		this.person_auto_id_fkey = person_auto_id_fkey;
	}

	// create schedule for event
	public Schedule(int from_hour, int from_minute, int from_month, int from_day, int from_year, int to_hour,
			int to_minute, int to_month, int to_day, int to_year, String event_title_fkey) {
		this.from_hour = from_hour;
		this.from_minute = from_minute;
		this.from_month = from_month;
		this.from_day = from_day;
		this.from_year = from_year;
		this.to_hour = to_hour;
		this.to_minute = to_minute;
		this.to_month = to_month;
		this.to_day = to_day;
		this.to_year = to_year;
		this.event_title_fkey = event_title_fkey;
	}

	//get from db for event
	public Schedule(int schedule_auto_id, int from_hour, int from_minute, int from_month, int from_day, int from_year,
			int to_hour, int to_minute, int to_month, int to_day, int to_year, String event_title_fkey) {
		this.schedule_auto_id = schedule_auto_id;
		this.from_hour = from_hour;
		this.from_minute = from_minute;
		this.from_month = from_month;
		this.from_day = from_day;
		this.from_year = from_year;
		this.to_hour = to_hour;
		this.to_minute = to_minute;
		this.to_month = to_month;
		this.to_day = to_day;
		this.to_year = to_year;
		this.event_title_fkey = event_title_fkey;
	}
	//get from db for person
	public Schedule(int schedule_auto_id, int from_hour, int from_minute, int from_month, int from_day, int from_year,
			int to_hour, int to_minute, int to_month, int to_day, int to_year, boolean available,
			int person_auto_id_fkey) {
		this.schedule_auto_id = schedule_auto_id;
		this.from_hour = from_hour;
		this.from_minute = from_minute;
		this.from_month = from_month;
		this.from_day = from_day;
		this.from_year = from_year;
		this.to_hour = to_hour;
		this.to_minute = to_minute;
		this.to_month = to_month;
		this.to_day = to_day;
		this.to_year = to_year;
		this.available = available;
		this.person_auto_id_fkey = person_auto_id_fkey;
	}

	public int getSchedule_auto_id() {
		return schedule_auto_id;
	}

	public int getFrom_hour() {
		return from_hour;
	}

	public int getFrom_minute() {
		return from_minute;
	}

	public int getFrom_month() {
		return from_month;
	}

	public int getFrom_day() {
		return from_day;
	}

	public int getFrom_year() {
		return from_year;
	}

	public int getTo_hour() {
		return to_hour;
	}

	public int getTo_minute() {
		return to_minute;
	}

	public int getTo_month() {
		return to_month;
	}

	public int getTo_day() {
		return to_day;
	}

	public int getTo_year() {
		return to_year;
	}

	public boolean isAvailable() {
		return available;
	}

	public int getPerson_auto_id_fkey() {
		return person_auto_id_fkey;
	}

	public String getEvent_title_fkey() {
		return event_title_fkey;
	}	
}
