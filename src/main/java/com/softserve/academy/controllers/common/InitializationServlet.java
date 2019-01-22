package com.softserve.academy.controllers.common;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.softserve.academy.IocContainer;
import com.softserve.academy.services.TripService;
import com.softserve.academy.services.UserService;
import com.softserve.academy.services.UserTripService;

/**
 * Servlet implementation class UserProfileUpdateServlet
 */
@WebServlet("/initialization")

public class InitializationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService;
	private TripService tripService;


    public InitializationServlet() {
        userService = IocContainer.get().getUserService();
        tripService = IocContainer.get().getTripService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		//initialization of tables creation
		userService.initializeRole();
		userService.initializeUser();
		tripService.initializeTrip();

		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<meta http-equiv='Content-Type' content='text/html; charset=utf-8'/>");
		out.println("<meta http-equiv='refresh' content='5; url=" 
				+ request.getAttribute("loginUrl") + "' />");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>Initialization ...</h1>");
		out.println("</body>");
		out.println("</html>");
		//out.flush();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
