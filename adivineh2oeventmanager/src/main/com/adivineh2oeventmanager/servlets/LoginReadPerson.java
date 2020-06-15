package main.com.adivineh2oeventmanager.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.com.adivineh2oeventmanager.dao.PersonDAO;
import main.com.adivineh2oeventmanager.utilities.ConnectionManager;
import main.com.adivineh2oeventmanager.validator.PersonValidator;

@WebServlet("/loginReadPerson")
public class LoginReadPerson extends HttpServlet {

	private static final long serialVersionUID = 1L;
	PersonDAO personDAO;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PersonDAO personDAO = ConnectionManager.getPersonDAO();

		request.getRequestDispatcher("loginReadPerson.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String phone = !request.getParameter("phone").isBlank() ? request.getParameter("phone") : "";
		String pass = !request.getParameter("pass").isBlank() ? request.getParameter("pass") : "";


		if (PersonValidator.isValidLoginInfo(phone, pass)) {
			if (personDAO.isPhoneAndPassMatch(phone, pass)) {
				// get the user from db and set user in session
				request.getSession().setAttribute("currentPerson", personDAO.getPerson(phone));
				// redirect to readAllEvent
				RequestDispatcher rd = request.getRequestDispatcher("readAllEvent.jsp");
				rd.forward(request, response);
			} else {
				 request.getSession().setAttribute("message", "password and phone didn't match");
			}
		} else {
			request.getSession().setAttribute("message",
					"phone numbers must be only digits 10-12 length and password but be at least 8 length, contain one symbol, and one uppercase");
		}
		request.getRequestDispatcher("loginReadPerson.jsp").forward(request, response);
	}
}
