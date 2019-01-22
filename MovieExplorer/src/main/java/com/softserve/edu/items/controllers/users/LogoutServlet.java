package com.softserve.edu.items.controllers.users;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.softserve.edu.items.controllers.ViewUrls;

@WebServlet({"/logout"})
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Logout");
		response.setContentType("text/html");
		HttpSession session = request.getSession(false);
		if (session != null) {
			System.out.println("logout session.invalidate");
			session.invalidate();
		}
		getServletConfig()
		.getServletContext()
		.getRequestDispatcher(ViewUrls.LOGIN_JSP.toString())
		.forward(request, response);
	}
	
}
