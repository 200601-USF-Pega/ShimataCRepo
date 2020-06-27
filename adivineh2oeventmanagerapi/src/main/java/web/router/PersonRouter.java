package main.java.web.router;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

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

import main.java.dao.PersonDAO;
import main.java.model.Event;
import main.java.model.Message;
import main.java.model.Person;
import main.java.model.RegisterContainer;
import main.java.model.Schedule;
import main.java.model.Supply;
import main.java.web.ConnectionService;

//http://localhost:8080/adivineh2oeventmanagerapi/router/event/all

@Path("/router/person/")
public class PersonRouter {

	PersonDAO personDAO = ConnectionService.getPersonDAO();

	// ----------------------------------------------------//
	// --------------------------person---------------------//

	// add One Person/register + (Schedule )
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createPostPerson(Person person) {
		personDAO.createPostPerson(person);
		return Response.status(201).build();
	}

	@POST
	@Path("register")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createPostPersonRegister(RegisterContainer person_and_pass) {
		personDAO.createPostPersonRegister(person_and_pass.getPerson(), person_and_pass.getPass());
		return Response.status(201).build();
	}

	@POST
	@Path("schedule")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createPostPersonSchedule(Schedule schedule) {
		personDAO.createPostPersonSchedule(schedule);
		return Response.status(201).build();
	}

	// view All people | One Person/login + (schedules | supplies |
	// message_thread_messages)
	@GET
	@Path("all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response readGetPersonAll() {
		return Response.ok((HashMap<Integer, Person>) personDAO.readGetPersonAll()).build();
	}

	@GET
	@Path("{phone}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response readGetPerson(@PathParam("phone") String phone) {
		return Response.ok((Person) personDAO.readGetPerson(phone)).build();
	}

	@POST
	@Path("login/{phone}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response readGetPersonLogin(@PathParam("phone") String phone, String input_pass) {
		return Response.ok((Person) personDAO.readGetPersonLogin(phone, input_pass)).build();
	}

	@GET
	@Path("schedule_all/{person_auto_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response readGetPersonScheduleAll(@PathParam("person_auto_id") int person_auto_id) {
		return Response.ok((ArrayList<Schedule>) personDAO.readGetPersonScheduleAll(person_auto_id)).build();
	}

	@GET
	@Path("supply_all/{person_auto_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response readGetPersonSupplyAll(@PathParam("person_auto_id") int person_auto_id) {
		return Response.ok((HashMap<Integer, Supply>) personDAO.readGetPersonSupplyAll(person_auto_id)).build();
	}

	@GET
	@Path("message_all/message_thread_match/{person_auto_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response readGetPersonMessageAllMessageThreadMatch(@PathParam("person_auto_id") int person_auto_id) {
		return Response.ok((HashMap<Timestamp, ArrayList<Message>>) personDAO
				.readGetPersonMessageAllMessageThreadMatch(person_auto_id)).build();
	}

	// edit One Person + (pass)

	@PUT
	@Path("/person")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updatePutPerson(Person person) {
		personDAO.updatePutPerson(person);
		return Response.status(202).build();
	}

	@PUT
	@Path("pass/{phone}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updatePutPersonPass(@PathParam("phone") String phone, ArrayList<String> old_pass_new_pass) {
		personDAO.updatePutPersonPass(phone, old_pass_new_pass.get(0), old_pass_new_pass.get(1));
		return Response.status(202).build();
	}

	// delete One Person + (schedule)

	@DELETE
	@Path("{person_auto_id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteDeletePerson(Event event, @PathParam("person_auto_id") int person_auto_id) {
		personDAO.deleteDeletePerson(person_auto_id);
		return Response.status(204).build();
	}

	@DELETE
	@Path("schedule/{schedule_auto_id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteDeletePersonSchedule(@PathParam("schedule_auto_id") int schedule_auto_id) {
		personDAO.deleteDeletePersonSchedule(schedule_auto_id);
		return Response.status(204).build();
	}
}
