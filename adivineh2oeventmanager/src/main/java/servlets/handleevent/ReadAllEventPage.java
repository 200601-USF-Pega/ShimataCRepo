package main.java.servlets.handleevent;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.HttpMethodConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.dao.EventDAO;
import main.java.model.Event;
import main.java.utilities.ConnectionManager;

public class ReadAllEventPage extends HttpServlet {

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
		
		checkLoggedIn(request, response);
		String action = request.getParameter("action") == null ? "" : request.getParameter("action");
		// always check session fist and redirect to login if != exist

		try {
			switch (action) {
			case "DeleteEvent":
				System.out.println(request.getParameter("titleToDelete") + "meh");

				deleteEvent(request, response);
				break;
			case "ReadEventPage":
				readEventPage(request, response);
				break;
			default:
				readAllEvent(request, response);
				break;
			}
		} catch (SQLException e) {
			throw new ServletException(e);
		}
	}

	private void checkLoggedIn(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession().getAttribute("currentPerson") == null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("ReadPersonLoginPage");
			dispatcher.forward(request, response);
		}
	}

	private void readAllEvent(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		// don't set to session because we want this to fire every refresh incase new
		// items have been added
		List<Event> allEvent = eventDAO.getAllEvents();
		request.setAttribute("allEvent", allEvent);

		RequestDispatcher dispatcher = request.getRequestDispatcher("readAllEventPage.jsp");
		dispatcher.forward(request, response);

	}

	private void readEventPage(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		String titleToView = request.getParameter("titleToView");
		request.getSession().setAttribute("titleToView", titleToView);
		response.sendRedirect("ReadEventPage");

	}

	private void deleteEvent(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		String title = request.getParameter("titleToDelete");
		eventDAO.deleteEventByTitle(title);
		response.sendRedirect("ReadAllEventPage");
	}

}