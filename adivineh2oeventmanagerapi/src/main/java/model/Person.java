package main.java.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashMap;

//@DeclareRoles("personAdmin")
public class Person implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int person_person_auto_id;
	private String first_name;
	private String last_name;
	private String role;
	private int seats_available;
	private String phone;
	private String email;
	private String facebook;
	private int address_number;
	private String street_name;
	private String unit;
	private String city;
	private String state;
	private int zip;
	private String profile_image_url;
	private HashMap<String, Event> events;
	private HashMap<Integer, Supply> responsible_supplies;
	private HashMap<Timestamp, MessageThread> message_threads;
	private HashMap<Integer, Schedule> schedules;

	//all info when create new person from db
	//only need one setter and then pass empty data if the form that was submitted was role = beneficiary
	

	//no setter for person_person_auto_id it is only set when db constructs the person
	//not getter for password 
	//password probably shouldn't be in get user at all... only the check username and password
	
	//create
	public Person(String first_name, String last_name, String role, int seats_available, String phone,
			String email, String facebook, int address_number, String street_name, String unit, String city,
			String state, int zip, String profile_image_url) {
		this.first_name = first_name;
		this.last_name = last_name;
		this.role = role;
		this.seats_available = seats_available;
		this.phone = phone;
		this.email = email;
		this.facebook = facebook;
		this.address_number = address_number;
		this.street_name = street_name;
		this.unit = unit;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.profile_image_url = profile_image_url;
	}
	
	//no args for jersey
	public Person(){}
	
	//from db 
	public Person(int person_auto_id, String first_name, String last_name, String role, int seats_available, String phone,
			String email, String facebook, int address_number, String street_name, String unit, String city,
			String state, int zip, String profile_image_url) {
		this.person_person_auto_id = person_auto_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.role = role;
		this.seats_available = seats_available;
		this.phone = phone;
		this.email = email;
		this.facebook = facebook;
		this.address_number = address_number;
		this.street_name = street_name;
		this.unit = unit;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.profile_image_url = profile_image_url;
	}

	//getters
	
	public int getPerson_auto_id() {
		return person_person_auto_id;
	}
	
	public String getFirst_name() {
		return first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public String getRole() {
		return role;
	}

	public int getSeats_available() {
		return seats_available;
	}

	public String getPhone() {
		return phone;
	}

	public String getEmail() {
		return email;
	}

	public String getFacebook() {
		return facebook;
	}

	public int getAddress_number() {
		return address_number;
	}

	public String getStreet_name() {
		return street_name;
	}

	public String getUnit() {
		return unit;
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

	public String getProfile_image_url() {
		return profile_image_url;
	}
	
	public HashMap<String, Event> getEvents() {
		return events;
	}

	public HashMap<Integer, Supply> getResponsible_supplies() {
		return responsible_supplies;
	}

	public HashMap<Timestamp, MessageThread> getMessage_threads() {
		return message_threads;
	}

	public HashMap<Integer, Schedule> getSchedules() {
		return schedules;
	}

	//setters
	
	public void setEvents(HashMap<String, Event> events) {
		this.events = events;
	}

	public void setResponsible_supplies(HashMap<Integer, Supply> responsible_supplies) {
		this.responsible_supplies = responsible_supplies;
	}

	public void setMessage_threads(HashMap<Timestamp, MessageThread> message_threads) {
		this.message_threads = message_threads;
	}

	public void setSchedules(HashMap<Integer, Schedule> schedules) {
		this.schedules = schedules;
	}	
}
