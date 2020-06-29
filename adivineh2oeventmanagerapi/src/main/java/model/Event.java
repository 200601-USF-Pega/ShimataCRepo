package main.java.model;

import java.io.Serializable;
import java.util.HashMap;

public class Event implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String title;
	private String info_url;
	private int address_number;
	private String street_name;
	private String city;
	private String state;
	private int zip;
	private int number_helpers_needed;
	private String event_image_url;
	private HashMap<Integer, Person> people;
	private HashMap<Integer, Supply> supplies;
	private MessageThread message_thread;
	private Schedule schedule;
	
	//no args for jersey
	public Event(){}
	
	public Event(String title, String info_url, int address_number, String street_name, String city, String state,
			int zip, int number_helpers_needed, String event_image_url) {
		this.title = title;
		this.info_url = info_url;
		this.address_number = address_number;
		this.street_name = street_name;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.number_helpers_needed = number_helpers_needed;
		this.event_image_url = event_image_url;
	}
	
	//getters
	public String getTitle() {
		return title;
	}

	public String getInfo_url() {
		return info_url;
	}

	public int getAddress_number() {
		return address_number;
	}

	public String getStreet_name() {
		return street_name;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public int getZip() {
		return zip;
	}

	public int getNumber_helpers_needed() {
		return number_helpers_needed;
	}

	public String getEvent_image_url() {
		return event_image_url;
	}

	public HashMap<Integer, Person> getPeople() {
		return people;
	}

	public HashMap<Integer, Supply> getSupplies() {
		return supplies;
	}

	public MessageThread getMessage_thread() {
		return message_thread;
	}

	public Schedule getSchedule() {
		return schedule;
	}

	//setters

	public void setPeople(HashMap<Integer, Person> people) {
		this.people = people;
	}

	public void setSupplies(HashMap<Integer, Supply> supplies) {
		this.supplies = supplies;
	}

	public void setMessage_thread(MessageThread message_thread) {
		this.message_thread = message_thread;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}
}
