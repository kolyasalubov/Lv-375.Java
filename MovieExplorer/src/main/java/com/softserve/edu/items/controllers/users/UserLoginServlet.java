package com.softserve.edu.items.controllers.users;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.softserve.edu.items.IocContainer;
import com.softserve.edu.items.controllers.ControllerUrls;
import com.softserve.edu.items.controllers.ViewUrls;
import com.softserve.edu.items.dto.LoginDto;
import com.softserve.edu.items.services.UserService;

@WebServlet({"/", "/login" })
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
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (isFirstStart) {
			isFirstStart = false;
//			request.setAttribute(ControllerUrls.LOGIN_SERVLET.toString(),
//					request.getRequestURL().toString());
		}
		
			HttpSession session = request.getSession();
			if (session == null || session.getAttribute("login") == null || session.getAttribute("password") == null) {
				getServletConfig()
				.getServletContext()
				.getRequestDispatcher(ViewUrls.LOGIN_JSP.toString())
				.forward(request, response);
			} else {
				LoginDto loginDto = new LoginDto(session.getAttribute("login").toString(), 
												 session.getAttribute("password").toString());
				if (userService.isValid(loginDto)) {
					getServletConfig()
					.getServletContext()
					.getRequestDispatcher(ViewUrls.MOVIES_LIST_JSP.toString())
					.forward(request, response);
				} else {
					getServletConfig()
					.getServletContext()
					.getRequestDispatcher(ViewUrls.LOGIN_JSP.toString())
					.forward(request, response);
				}
				
			}
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final int expiryTime = 30*60;
		LoginDto loginDto = new LoginDto(
				request.getParameter("login"),
				request.getParameter("password"));
		if (userService.isValid(loginDto)) {
			System.out.println("isValid");
			//generate new session and set attribute
			HttpSession session = request.getSession();
			session.setAttribute("login", loginDto.getLogin());
			session.setAttribute("password", loginDto.getPassword());
			
			//setting session to expire in 30 minutes
			session.setMaxInactiveInterval(expiryTime);
			
			//creating cookie
			Cookie loginCookie = new Cookie("login", loginDto.getLogin());
			Cookie passwordCookie = new Cookie("password", loginDto.getPassword());
			loginCookie.setMaxAge(expiryTime);
			passwordCookie.setMaxAge(expiryTime);
			response.addCookie(loginCookie);
			response.addCookie(passwordCookie);
			response.sendRedirect(request.getContextPath()
					+ ControllerUrls.MOVIES_LIST.toString());
		} else {
			// Show Error Validator
			request.setAttribute("error", "Either email or password is wrong");
			getServletConfig()
				.getServletContext()
				.getRequestDispatcher(ViewUrls.LOGIN_JSP.toString())
				.forward(request, response);
		}
	}

}

