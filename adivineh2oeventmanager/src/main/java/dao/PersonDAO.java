package main.java.dao;


import main.java.model.Person;


public interface PersonDAO {
	
	public boolean isPhoneAndPassMatch(String phone, String input_pass);

	public Person getPerson(String phone);

	public boolean updatePerson(Person person);

	public boolean updatePersonPass(String new_pass, String phone);

}
