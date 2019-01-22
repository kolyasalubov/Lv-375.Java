package com.softserve.academy.controllers.users;

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
import com.softserve.academy.DTO.TripDto;
import com.softserve.academy.DTO.UserDto;
import com.softserve.academy.controllers.ControllerUrls;
import com.softserve.academy.controllers.ViewUrls;
import com.softserve.academy.services.TripService;
import com.softserve.academy.services.UserService;

/**
 * Servlet implementation class UserProfileEditServlet
 */
@WebServlet("/useredit")
public class UserProfileEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserProfileEditServlet() {
        super();
        userService = IocContainer.get().getUserService();
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
		isActiveSession = isActiveSession && (session != null) && (session.getAttribute("loginDto") != null)
				&& (((LoginDto) (session.getAttribute("loginDto"))).getLogin() != null) && (idSessionCookie != null);
		isActiveSession = isActiveSession && (idSessionCookie.getValue().equals(session.getId()));
		//
		if (!isActiveSession) {
			getServletConfig().getServletContext().getRequestDispatcher(ControllerUrls.LOGOUT_SERVLET.toString())
					.forward(request, response);
		} else {
			UserDto userDto = userService.getUserDto((LoginDto)session.getAttribute("loginDto"));
			request.setAttribute("userDto", userDto);
			getServletConfig().getServletContext().getRequestDispatcher(ViewUrls.USER_EDIT_JSP.toString())
					.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
        //Set new values to user
			UserDto userDto = new UserDto (request.getParameter("login"),
					request.getParameter("password"), 
					request.getParameter("alias"), 
					request.getParameter("role"));
						userService.setUserDto(userDto);
						
					getServletConfig().getServletContext().getRequestDispatcher(ControllerUrls.USER_TRIP_SERVLET.toString())
					.forward(request, response);
		}
		}

}
