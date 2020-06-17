package main.java.servlets.handleevent;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.dao.EventDAO;
import main.java.model.Event;
import main.java.utilities.ConnectionManager;

public class ReadEventPage extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private EventDAO eventDAO;

	public void init() {
		eventDAO = ConnectionManager.getEventDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		System.out.println(request.getAttribute("titleToView" + "not exist"));
		String action = request.getParameter("action") == null ? "" : request.getParameter("action");
		try {
			switch (action) {
			case "ReadAllPersonPage":
				readAllPersonPage(request, response);
				break;
			case "DeletePerson":
				deletePerson(request, response);
				break;
			case "UpdateEvent":
				updateEvent(request, response);
				break;
			default:
				readEvent(request, response);
				break;
			}
		} catch (SQLException e) {
			throw new ServletException(e);
		}
	}

	private void readAllPersonPage(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		String title = request.getParameter("titleToView");
		Event selectedEvent = eventDAO.getEventByTitle(title);
		RequestDispatcher dispatcher = request.getRequestDispatcher("ReadEventPage");
		request.setAttribute("event", selectedEvent);
		dispatcher.forward(request, response);
	}

	private void deletePerson(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		String title = request.getParameter("titleToDelete");
		System.out.println(title);
		eventDAO.deleteEventByTitle(title);
		response.sendRedirect("ReadAllEventPage");
	}

	private void updateEvent(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		System.out.println("got it");
		Event updatedEvent = new Event(request.getParameter("title"), request.getParameter("info_url"),
				Integer.parseInt(request.getParameter("address_number")), request.getParameter("street_name"),
				request.getParameter("city"), request.getParameter("state"),
				Integer.parseInt(request.getParameter("zip")),
				Integer.parseInt(request.getParameter("number_helpers_needed")),
				request.getParameter("event_image_url"));
		eventDAO.updateEvent(updatedEvent, (String) request.getSession().getAttribute("titleToView"));
		request.getSession().setAttribute("titleToView", updatedEvent.getTitle());
		response.sendRedirect("ReadEventPage");

	}

	private void readEvent(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		String titleToView = (String) request.getSession().getAttribute("titleToView");
		request.setAttribute("selectedEvent", eventDAO.getEventByTitle(titleToView));
		RequestDispatcher dispatcher = request.getRequestDispatcher("readEventPage.jsp");
		dispatcher.forward(request, response);
	}

}