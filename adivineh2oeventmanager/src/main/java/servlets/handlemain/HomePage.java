package main.java.servlets.handlemain;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomePage extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/CreatePersonRegisterPage":
				createPersonRegisterPage(request, response);
				break;
			case "/ReadPersonLoginPage":
				readPersonLoginPage(request, response);
				break;
			default:
				homePage(request, response);
				break;
			}
		} catch (SQLException e) {
			throw new ServletException(e);
		}
	}
	
	private void homePage(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/homePage.jsp");
		dispatcher.forward(request, response);
	}
	
	private void createPersonRegisterPage(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/CreatePersonRegisterPage");
		dispatcher.forward(request, response);
	}
	
	private void readPersonLoginPage(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		RequestDispatcher dispatcher = request.getRequestDispatcher("/ReadPersonLoginPage");
		dispatcher.forward(request, response);
	}

}