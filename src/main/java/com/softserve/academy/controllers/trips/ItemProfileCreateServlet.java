package com.softserve.academy.controllers.trips;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.softserve.academy.DTO.TripDto;
import com.softserve.academy.controllers.ControllerUrls;
import com.softserve.academy.controllers.ViewUrls;
import com.softserve.academy.services.TripService;
import com.softserve.academy.services.UserService;
import com.softserve.academy.DTO.TripDto;
import com.softserve.academy.IocContainer;
import com.softserve.academy.DTO.LoginDto;

/**
 * Servlet implementation class ItemProfileCreateServlet
 */
@WebServlet("/tripcreate")
public class ItemProfileCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService;
	private TripService tripServise;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ItemProfileCreateServlet() {
		super();
		userService = IocContainer.get().getUserService();
		tripServise = IocContainer.get().getTripService();
		}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

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
		isActiveSession = isActiveSession && (session != null) && (session.getAttribute("loginDto") != null)
				&& (((LoginDto) (session.getAttribute("loginDto"))).getLogin() != null) && (idSessionCookie != null);
		isActiveSession = isActiveSession && (idSessionCookie.getValue().equals(session.getId()));
		
		//Logout if session is not active
		if (!isActiveSession) {
			getServletConfig().getServletContext().getRequestDispatcher(ControllerUrls.LOGOUT_SERVLET.toString())
					.forward(request, response);
		} else {
        //Give empty trip object to user
			TripDto tripDto = new TripDto(0l, "", "", "", "", "");
			request.setAttribute("tripDto", tripDto);
			getServletConfig().getServletContext().getRequestDispatcher(ViewUrls.TRIP_PROFILE_JSP.toString())
					.forward(request, response);
		}
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
