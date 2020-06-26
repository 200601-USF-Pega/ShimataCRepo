package main.java.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

import main.java.model.Message;
import main.java.model.Person;
import main.java.model.Schedule;
import main.java.model.Supply;

public interface PersonDAO {

	// add One Person/register + (Schedule )
	//(person+event is in the event dao)
	public boolean createPostPerson(Person person);

	public boolean createPostPersonRegister(Person person, String pass);

	//check !scheduleTable.contains(schedule)
	public boolean createPostPersonSchedule(Schedule schedule);
		
	// view All people | One Person/login + (schedules | supplies |
	// message_thread_messages)
	public HashMap<Integer, Person> readGetPersonAll();

	public Person readGetPerson(String phone);

	public Person readGetPersonLogin(String phone, String input_pass);
	
	public ArrayList<Schedule> readGetPersonScheduleAll(int person_auto_id);
	
	public HashMap<Integer, Supply>  readGetPersonSupplyAll(int responsible_person_auto_id_fkey);
	
	public HashMap<Timestamp ,ArrayList<Message>> readGetPersonMessageAllMessageThreadMatch(int person_auto_id_fkey);

	// edit One Person + (pass | 
	public boolean updatePutPerson(Person person);

	public boolean updatePutPersonPass(String phone, String old_pass, String new_pass);

	// delete One Person + (schedule)
	public boolean deleteDeletePerson(int person_auto_id);
	
	public boolean deleteDeletePersonSchedule(int schedule_auto_id);


}
