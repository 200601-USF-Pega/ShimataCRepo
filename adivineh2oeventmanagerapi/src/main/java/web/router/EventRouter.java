package main.java.web.router;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import main.java.dao.EventDAO;
import main.java.dao.PersonDAO;
import main.java.model.Event;
import main.java.model.Message;
import main.java.model.MessageThread;
import main.java.model.Person;
import main.java.model.RegisterContainer;
import main.java.model.Schedule;
import main.java.model.Supply;
import main.java.web.ConnectionService;

//http://localhost:8080/adivineh2oeventmanagerapi/router/event/all

@Path("/router/event/")
public class EventRouter {

	EventDAO eventDAO = ConnectionService.getEventDAO();

	// add One Event + (Person | Supply | Schedule | MessageThread | Message)
	@POST
	@Path("/event")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createPostEvent(Event event) {
		eventDAO.createPostEvent(event);
		return Response.status(201).build();
	}

	@POST
	@Path("person/{title}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createPostPersonToEvent(@PathParam("title") String title, int person_auto_id) {
		eventDAO.createPostPersonToEvent(title, person_auto_id);
		return Response.status(201).build();
	}

	@POST
	@Path("supply")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createPostEventSupply(Supply supply) {
		eventDAO.createPostEventSupply(supply);
		return Response.status(201).build();
	}

	@POST
	@Path("schedule")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createPostEventSchedule(Schedule schedule) {
		eventDAO.createPostEventSchedule(schedule);
		return Response.status(201).build();
	}

	@POST
	@Path("message_thread")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createPostEventMessageThread(MessageThread message_thread) {
		eventDAO.createPostEventMessageThread(message_thread);
		return Response.status(201).build();
	}

	@POST
	@Path("message_thread/message")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createPostMessageThreadMessage(Message message) {
		eventDAO.createPostMessageThreadMessage(message);
		return Response.status(201).build();
	}

	// view All Events | One Event + (its schedule | message thread messages | its
	// supplies | all its People)
	@GET
	@Path("all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response readGetEventAll() {
		return Response.ok((HashMap<String, Event>) eventDAO.readGetEventAll()).build();
	}

	@GET
	@Path("{title}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response readGetEvent(@PathParam("title") String title) {
		return Response.ok((Event) eventDAO.readGetEvent(title)).build();
	}

	@GET
	@Path("schedule/{title}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response readGetEventSchedule(@PathParam("title") String title) {
		return Response.ok((Schedule) eventDAO.readGetEventSchedule(title)).build();
	}

	@GET
	@Path("message_thread/message_all/{title}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response readGetEventMessageThreadMessageAll(@PathParam("title") String title) {
		return Response.ok((ArrayList<Message>) eventDAO.readGetEventMessageThreadMessageAll(title)).build();
	}

	@GET
	@Path("supply_all/{title}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response readGetEventSupplyAll(@PathParam("title") String title) {
		return Response.ok((HashMap<Integer, Supply>) eventDAO.readGetEventSupplyAll(title)).build();
	}

	@GET
	@Path("person_all/{title}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response readGetEventPersonAll(@PathParam("title") String title) {
		return Response.ok((HashMap<Integer, Person>) eventDAO.readGetEventPersonAll(title)).build();
	}

	// change One Event + (Supply | Schedule)
	@PUT
	@Path("{original_title}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updatePutEvent(Event event, @PathParam("original_title") String original_title) {
		eventDAO.updatePutEvent(event, original_title);
		return Response.status(202).build();
	}

	@PUT
	@Path("supply")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updatePutEventSupply(Supply supply) {
		eventDAO.updatePutEventSupply(supply);
		return Response.status(202).build();
	}

	@PUT
	@Path("schedule")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updatePutEventSchedule(Schedule schedule) {
		eventDAO.updatePutEventSchedule(schedule);
		return Response.status(202).build();
	}

	// delete One event + (Supply | Person)

	@DELETE
	@Path("{title}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteDeleteEvent(Event event, @PathParam("title") String title) {
		eventDAO.deleteDeleteEvent(title);
		return Response.status(204).build();
	}

	@DELETE
	@Path("supply")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteDeleteEventSupply(int supply_auto_id) {
		eventDAO.deleteDeleteEventSupply(supply_auto_id);
		return Response.status(204).build();
	}

	@DELETE
	@Path("person/{title}/{person_auto_id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteDeletePersonFromEvent(@PathParam("title") String title, @PathParam("person_auto_id") int person_auto_id) {
		eventDAO.deleteDeletePersonFromEvent(title, person_auto_id);
		return Response.status(204).build();
	}
}
