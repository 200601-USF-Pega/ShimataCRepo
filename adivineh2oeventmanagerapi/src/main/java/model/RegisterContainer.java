package main.java.model;

public class RegisterContainer {
	private Person person;
	private String pass;

	public RegisterContainer(Person person, String pass) {
		super();
		this.person = person;
		this.pass = pass;
	}

	public Person getPerson() {
		return person;
	}

	public String getPass() {
		return pass;
	}
}
