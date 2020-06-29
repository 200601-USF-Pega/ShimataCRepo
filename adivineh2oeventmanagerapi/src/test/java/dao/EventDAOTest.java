package test.java.dao;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import main.java.dao.impl.EventDAOImpl;
import main.java.model.Event;
import main.java.model.Person;




//so mockito can process the annotations

@RunWith(MockitoJUnitRunner.class)
public class EventDAOTest {
	//the service where mocked dependencies will go
	@InjectMocks
	EventDAOImpl eventdaoimpl;
	
	//dao gets annotated with at mock
	//annotation instantiates them and then injects them
	@Mock
	Event event;

	@Mock
	Person person;

	@Test
	public void testCreatePostEvent() {
		boolean saved = eventdaoimpl.createPostEvent(event);
		assertEquals(true, saved);

		verify(event, times(1)).getState();
		verify(person, times(1)).getEmail();
	}
}
