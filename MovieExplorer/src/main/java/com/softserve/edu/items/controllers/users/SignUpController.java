package com.softserve.edu.items.controllers.users;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.softserve.edu.items.IocContainer;
import com.softserve.edu.items.controllers.ControllerUrls;
import com.softserve.edu.items.controllers.ViewUrls;
import com.softserve.edu.items.dto.UsersDto;
import com.softserve.edu.items.services.UserService;



@WebServlet({ "/signUp" })
public class SignUpController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private UserService userService;
	
	public SignUpController() {
		UserService userService = IocContainer.get().getUserService();
		this.userService = userService;
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getServletConfig()
			.getServletContext()
			.getRequestDispatcher(ViewUrls.SIGN_UP_JSP.toString()).forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Fullname: " + request.getParameter("fullname") +
					 "Password: " + request.getParameter("password") +
					 "Email: " + request.getParameter("email")
		);
		UsersDto usersDto = new UsersDto(request.getParameter("fullname"),
										request.getParameter("password"),
										request.getParameter("email"));
		boolean isCreated = userService.createUser(usersDto);
		if(isCreated) {
			response.sendRedirect(request.getContextPath()
			+ ControllerUrls.LOGIN_SERVLET.toString());
//			getServletConfig()
//			.getServletContext()
//			request
//			.getRequestDispatcher(ControllerUrls.LOGIN_SERVLET.toString())
//			.forward(request, response);
		} else {
			 //Show Error Validator
			request.setAttribute("error", "Bad Login or Password");
			getServletConfig()
			.getServletContext()
			.getRequestDispatcher(ViewUrls.SIGN_UP_JSP.toString()).forward(request, response);
		}
	}
	
	

}
