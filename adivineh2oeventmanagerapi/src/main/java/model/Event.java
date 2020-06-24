package main.java.model;

import java.io.Serializable;

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
	

	public Event(String title, String info_url, int address_number, String street_name, String city, String state, int zip, int number_helpers_needed, String event_image_url)
	{
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getInfo_url() {
		return info_url;
	}

	public void setInfo_url(String info_url) {
		this.info_url = info_url;
	}

	public int getAddress_number() {
		return address_number;
	}

	public void setAddress_number(int address_number) {
		this.address_number = address_number;
	}

	public String getStreet_name() {
		return street_name;
	}

	public void setStreet_name(String street_name) {
		this.street_name = street_name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}

	public int getNumber_helpers_needed() {
		return number_helpers_needed;
	}

	public void setNumber_helpers_needed(int number_helpers_needed) {
		this.number_helpers_needed = number_helpers_needed;
	}

	public String getEvent_image_url() {
		return event_image_url;
	}

	public void setEvent_image_url(String event_image_url) {
		this.event_image_url = event_image_url;
	}
}
