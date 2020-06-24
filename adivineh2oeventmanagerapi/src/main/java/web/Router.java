package main.java.web;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import main.java.dao.EventDAO;
import main.java.model.Event;

//http://localhost:8080/adivineh2oeventmanager/router/event/all

@Path("/router")
public class Router {

	EventDAO eventDAO = ConnectionService.getEventDAO();

	@GET
	@Path("/event/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllEvents() {
		System.out.println("hit get all");
		return Response.ok((ArrayList<Event>) eventDAO.getAllEvents()).build();
	}

}
