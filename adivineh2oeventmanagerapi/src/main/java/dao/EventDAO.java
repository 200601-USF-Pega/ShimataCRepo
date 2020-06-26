package main.java.dao;

import java.util.ArrayList;
import java.util.HashMap;

import main.java.model.Event;
import main.java.model.Message;
import main.java.model.MessageThread;
import main.java.model.Person;
import main.java.model.Schedule;
import main.java.model.Supply;

public interface EventDAO {

	// add One Event + (Person | Supply | Schedule | MessageThread | Message)
	public boolean createPostEvent(Event event);

	// !person.schedule.contains(event.schedule)
	public boolean createPostPersonToEvent(String title, int person_auto_id);

	public boolean createPostEventSupply(Supply supply);

	public boolean createPostEventSchedule(Schedule schedule);

	public boolean createPostEventMessageThread(MessageThread message_thread);

	public boolean createPostMessageThreadMessage(Message message);

	// view All Events | One Event + (its schedule | message thread messages | its
	// supplies |
	// all its People)
	public HashMap<String, Event> readGetEventAll();

	public Event readGetEvent(String title);

	public Schedule readGetEventSchedule(String title);

	// you must have the message_thread to get the messages
	// int the future change this so that message also holds event_title
	// or the person to sort by message threads
	public ArrayList<Message> readGetEventMessageThreadMessageAll(String title);

	public HashMap<Integer, Supply> readGetEventSupplyAll(String title);
	// in future should make get one supply instead of loading all supplies
	// as a giant list of full objects and passing the object through the forward

	public HashMap<Integer, Person> readGetEventPersonAll(String title);

	// change One Event + (Supply | Schedule)
	public boolean updatePutEvent(Event event, String original_title);

	public boolean updatePutEventSupply(Supply supply);

	public boolean updatePutEventSchedule(Schedule schedule);

	// delete One event + (Supply | Person)
	public boolean deleteDeleteEvent(String title);

	public boolean deleteDeleteEventSupply(int supply_auto_id);

	// do not delete schedule.. only alter it.. an event should always have a
	// schedule once it is planned out

	public boolean deleteDeletePersonFromEvent(String title, int person_auto_id);

}
