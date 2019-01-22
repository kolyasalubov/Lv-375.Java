package com.softserve.academy.controllers.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.softserve.academy.IocContainer;
import com.softserve.academy.DTO.LoginDto;
import com.softserve.academy.DTO.UserTripDto;
import com.softserve.academy.controllers.ControllerUrls;
import com.softserve.academy.controllers.ViewUrls;



/**
 * Servlet implementation class UserProfileEditServlet
 */
@WebServlet("/usertrip")
public class UserTripServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserTripServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
				boolean isActiveSession = true;
		HttpSession session = request.getSession(false); // Do not Create new Session
		Cookie idSessionCookie = null;
		for (Cookie currentCookie : request.getCookies()) {
			if (currentCookie.getName().equals("id_session")) {
				idSessionCookie = currentCookie;
				break;
			}
		}
		//Check if session, loginDto, idSessionCookie is not null
		isActiveSession = isActiveSession && (session != null)
				&& (session.getAttribute("loginDto") != null)
				&& (((LoginDto) (session.getAttribute("loginDto"))).getLogin() != null)
				&& (idSessionCookie != null);
		isActiveSession = isActiveSession
				&& (idSessionCookie.getValue().equals(session.getId()));
		
		//Logout if session is not active
		if (!isActiveSession) {
			getServletConfig()
				.getServletContext()
				.getRequestDispatcher(ControllerUrls.LOGOUT_SERVLET.toString())
				.forward(request, response);
		} else {
			// Get all trips of current user
			UserTripDto userTripDto = IocContainer.get()
					.getUserTripService()
					.getUserTrips(IocContainer.get()
					.getUserService()
					.getUserDto(((LoginDto)(request.getSession(false).getAttribute("loginDto")))));
			//Adding userTripDto to request object
			request.setAttribute("userTripDto", userTripDto);
			//Adding size of List to request object
			request.setAttribute("countTrips",
					String.valueOf(userTripDto.getTrips().size()));
			
			Cookie visibleItemsCookie = null;
			for (Cookie currentCookie : request.getCookies()) {
				if (currentCookie.getName().equals("visible_items")) {
					visibleItemsCookie = currentCookie;
					break;
				}
			}
			String visibleItems = "100000";
			if (visibleItemsCookie != null) {
				visibleItems = visibleItemsCookie.getValue();
			}
			request.setAttribute("visibleItems", visibleItems);
			//
			int pageNumber = 5;
			if (request.getParameter("pageNumber") != null) {
				pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
			}
			request.setAttribute("pageNumber",
					String.valueOf(pageNumber));
			
			//Go to view with user's trips
			getServletConfig()
				.getServletContext()
				.getRequestDispatcher(ViewUrls.USER_TRIP_JSP.toString())
				.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
