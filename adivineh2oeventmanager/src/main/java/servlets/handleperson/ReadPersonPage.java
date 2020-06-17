package main.java.servlets.handleperson;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.dao.PersonDAO;
import main.java.model.Person;
import main.java.utilities.ConnectionManager;

public class ReadPersonPage extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PersonDAO personDAO;

	public void init() {
		personDAO = ConnectionManager.getPersonDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//next sprint:
		//make an interfaces that have 
		//types of crud operations all and one generic objects
		//make an abstract class that implements them
		//make a couple abstract classes that implement them
		//extend the abstract classes and overwrite if necessary
		//much less duplicate code!!
		checkLoggedIn(request, response);
		String action = request.getParameter("action") == null ? "" : request.getParameter("action");
		try {
			switch (action) {
			case "UpdatePerson":
				updatePerson(request, response);
				break;
			default:
				readPerson(request, response);
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

	private void updatePerson(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
	}

	private void readPerson(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		//sometimes the session person will not be the person to load
		//also later if loaded from non-admin some sontent should not be 
		//available in the url or html to view 
		Person selectedPerson = (Person) request.getSession().getAttribute("currentPerson");
		RequestDispatcher dispatcher = request.getRequestDispatcher("readPersonPage.jsp");
		request.setAttribute("selectedPerson", selectedPerson);
		dispatcher.forward(request, response);
	}
}