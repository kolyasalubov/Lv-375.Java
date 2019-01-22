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
import com.softserve.academy.DTO.UserDto;
import com.softserve.academy.controllers.ControllerUrls;
import com.softserve.academy.controllers.ViewUrls;
import com.softserve.academy.services.UserService;

@WebServlet("/usercreate")
public class UserProfileCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService;

	public UserProfileCreateServlet() {
		super();
		userService = IocContainer.get().getUserService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		getServletConfig().getServletContext().getRequestDispatcher(ViewUrls.USER_PROFILE_JSP.toString())
				.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//Get values from user
		UserDto userDto = new UserDto(request.getParameter("login"), 
				request.getParameter("password"), 
				request.getParameter("alias"), 
				request.getParameter("role"));
		
		//LoginDTO create to assign it to the current session
		LoginDto loginDto = new LoginDto (request.getParameter("login"), 
				request.getParameter("password"));

				userService.insertUser(userDto);
				// Create session
				HttpSession session = request.getSession(true);
				session.setAttribute("loginDto", loginDto);
				session.setAttribute("currentSessionUser", loginDto.getLogin());
				session.setMaxInactiveInterval(300000);
				// Add Cookie
				Cookie cookie = new Cookie("id_session", session.getId());
				response.addCookie(cookie);
				//Redirect to 
				response.sendRedirect(request.getContextPath() + ControllerUrls.ALL_TRIP_SERVLET.toString());

			}
	
}
