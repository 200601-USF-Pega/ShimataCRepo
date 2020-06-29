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

@WebServlet(value = "/ReadPersonLoginPage")
public class ReadPersonLoginPage extends HttpServlet {

	private static final long serialVersionUID = 1L;
	//logger
	static final Logger logger = Logger.getLogger(ReadPersonLoginPage.class);
	
	PersonDAO personDAO = ConnectionManager.getPersonDAO();

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		BasicConfigurator.configure();
		req.getRequestDispatcher("readPersonLoginPage.jsp").forward(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		login(req, resp);
	}
	
	private void login(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		String phone = !req.getParameter("phone").isBlank() ? req.getParameter("phone") : "";
		String pass = !req.getParameter("pass").isBlank() ? req.getParameter("pass") : "";
		
		if (PersonValidator.isValidLoginInfo(phone, pass)) {
			if (personDAO.isPhoneAndPassMatch(phone, pass)) {
				//logger impl 
				logger.debug("person with phone" + phone + "had a db matching password");
				logger.info("logging a message");
				// get the user from db and set user in session
				req.getSession().setAttribute("currentPerson", personDAO.getPerson(phone));

				// redirect to readAllEvent
//				resp.sendRedirect("ReadAllEventPage");
				req.getRequestDispatcher("ReadAllEventPage").forward(req, resp);

				
			} else {
				req.getSession().setAttribute("message", "password and phone didn't match");
				req.getRequestDispatcher("readPersonLoginPage.jsp").forward(req, resp);
			}
		} else {
			req.getSession().setAttribute("message",
					"phone numbers must be only digits 10-12 length and password but be at least 8 length, contain one symbol, and one uppercase");
			req.getRequestDispatcher("readPersonLoginPage.jsp").forward(req, resp);
		}
	}
}
