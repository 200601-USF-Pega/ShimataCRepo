package main.com.adivineh2oeventmanager.dao;

import java.util.List;

import main.com.adivineh2oeventmanager.model.Event;

public interface EventDAO {

	public boolean addEvent(Event event);

	public List<Event> getAllEvents();

	public boolean deleteEventByTitle(String title);
	
	public Event getEventByTitle(String title);
	
	//in the future when you add person to event check if !person.schedule.contains(event.schedule)
	public boolean addPersonToEvent(int auto_id, String title);


}
