package main.java.model;

public class Person {
	private int auto_id;
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

	//all info when create new person from db
	//only need one setter and then pass empty data if the form that was submitted was role = beneficiary
	
	//construct from db
	public Person(int auto_id, String first_name, String last_name, String role, int seats_available,
			String phone, String email, String facebook, int address_number, String street_name, String unit,
			String city, String state, int zip, String profile_image_url) {
		this.auto_id = auto_id;
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
	
	//construct from input
	public Person(String first_name, String last_name, String role, int seats_available,
			String phone, String email, String facebook, int address_number, String street_name, String unit,
			String city, String state, int zip, String profile_image_url) {
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
	
	
	//no setter for auto_id it is only set when db constructs the person
	//not getter for password 
	//password probably shouldn't be in get user at all... only the check username and password
	
	public int getAuto_id() {
		return auto_id;
	}
	
	//update pass -- this entire thing should be in the dao not the model
//	public void setPass(String newPass, String oldPass) {
		//can model call on the daoImpl to get the hashed pass? inappropriate?
		//because we don't retrieve it except for login check
//		if hashedDBPass.unhash() == oldPass {
			//newPass = newPass.hash();
//			this.pass = newPass; ///hash the pass
		//}
//	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getSeats_available() {
		return seats_available;
	}

	public void setSeats_available(int seats_available) {
		this.seats_available = seats_available;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
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

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
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

	public String getProfile_image_url() {
		return profile_image_url;
	}

	public void setProfile_image_url(String profile_image_url) {
		this.profile_image_url = profile_image_url;
	}	
	
}
