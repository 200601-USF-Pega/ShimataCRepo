package main.java.servlets.handleloginreg;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import main.java.dao.PersonDAO;
import main.java.model.Person;
import main.java.utilities.ConnectionManager;
import main.java.validator.PersonValidator;

public class ReadPersonLoginPage extends HttpServlet {

	private static final long serialVersionUID = 1L;
	static final Logger logger = Logger.getLogger(ReadPersonLoginPage.class);
	PersonDAO personDAO = ConnectionManager.getPersonDAO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		BasicConfigurator.configure();
		request.getRequestDispatcher("readPersonLoginPage.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String phone = !request.getParameter("phone").isBlank() ? request.getParameter("phone") : "";
		String pass = !request.getParameter("pass").isBlank() ? request.getParameter("pass") : "";

		if (PersonValidator.isValidLoginInfo(phone, pass)) {
			if (personDAO.isPhoneAndPassMatch(phone, pass)) {
				// logger.debug("person with phone" + phone + "had a db matching password");
				// get the user from db and set user in session
				request.getSession().setAttribute("currentPerson", personDAO.getPerson(phone));

				// redirect to readAllEvent
				response.sendRedirect("ReadAllEventPage");

			} else {
				request.getSession().setAttribute("message", "password and phone didn't match");
				request.getRequestDispatcher("readPersonLoginPage.jsp").forward(request, response);

			}
		} else {
			request.getSession().setAttribute("message",
					"phone numbers must be only digits 10-12 length and password but be at least 8 length, contain one symbol, and one uppercase");
			request.getRequestDispatcher("readPersonLoginPage.jsp").forward(request, response);

		}

	}
}
