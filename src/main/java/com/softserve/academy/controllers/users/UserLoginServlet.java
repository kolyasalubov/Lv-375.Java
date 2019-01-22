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
import com.softserve.academy.controllers.ControllerUrls;
import com.softserve.academy.controllers.ViewUrls;
import com.softserve.academy.DTO.LoginDto;
import com.softserve.academy.services.UserService;

/**
 * Servlet implementation class UserLoginServlet
 */
@WebServlet({ "/", "/login" })
public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService;
	private boolean isFirstStart;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLoginServlet() {
        super();
        userService = IocContainer.get().getUserService();
        isFirstStart = true;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//Check if this is first start and initialize table creation
		if (isFirstStart) {
			isFirstStart = false;
			request.setAttribute("loginUrl",	
					request.getRequestURL().toString());
//			getServletConfig()
//				.getServletContext()
//				.getRequestDispatcher("/initialization") 
//				.forward(request, response);
				//.include(request, response);
		} else {
			getServletConfig()
				.getServletContext()
				.getRequestDispatcher(ViewUrls.LOGIN_JSP.toString())
				.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		LoginDto loginDto = new LoginDto(
				request.getParameter("login"), // TODO
				request.getParameter("password"));
		System.out.println("loginDto = " + loginDto);
		if (userService.isValid(loginDto)) {
			
			//If user is admin then redirect to admin panel
		 	if(userService.isAdmin((loginDto))) {
			// Create session
			HttpSession session = request.getSession(true);
			session.setAttribute("loginDto", loginDto);
			session.setMaxInactiveInterval(600000);
			// Add Cookie
			Cookie cookie = new Cookie("id_session", session.getId());
			response.addCookie(cookie);
			
			response.sendRedirect(request.getContextPath()                     
					+ ControllerUrls.USERS_ADMIN_SERVLET.toString());
			
			} else {
			
			// Create session
			HttpSession session = request.getSession(true);
			session.setAttribute("loginDto", loginDto);
			session.setAttribute("currentSessionUser",loginDto.getLogin()); 
			session.setMaxInactiveInterval(600000);
			// Add Cookie
			Cookie cookie = new Cookie("id_session", session.getId());
			response.addCookie(cookie);
			//
			response.sendRedirect(request.getContextPath()              
					+ ControllerUrls.USER_TRIP_SERVLET.toString());     

			}
		} else {
			// Show Error Validator
			request.setAttribute("error", "Bad Login or Password");
			getServletConfig()
				.getServletContext()
				.getRequestDispatcher(ViewUrls.LOGIN_JSP.toString())
				.forward(request, response);
		}
	}

}
