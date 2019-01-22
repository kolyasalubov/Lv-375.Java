package com.softserve.edu.items.controllers.users;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.softserve.edu.items.controllers.ControllerUrl;
import com.softserve.edu.items.controllers.SessionManager;

/**
 * Servlet implementation class UserLogoutServlet
 */
@WebServlet(ControllerUrl.LOGOUT_SERVLET)
public class UserLogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLogoutServlet() {
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {

	// invalidate session
	if (SessionManager.checkSession(request)) {
	    request.getSession(false).invalidate();
	}
	response.sendRedirect(request.getContextPath() + ControllerUrl.LOGIN_SERVLET);
	// getServletConfig().getServletContext().getRequestDispatcher(ViewUrls.LOGIN_JSP.toString()).forward(request,
	// response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	doGet(request, response);
    }

}
