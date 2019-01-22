package com.softserve.edu.items.controllers.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.softserve.edu.items.IocContainer;
import com.softserve.edu.items.controllers.ControllerUrls;
import com.softserve.edu.items.controllers.ViewUrls;
import com.softserve.edu.items.dto.LoginDto;
import com.softserve.edu.items.dto.MovieDto;
import com.softserve.edu.items.dto.UsersDto;
import com.softserve.edu.items.entity.Role;
import com.softserve.edu.items.services.MovieService;
import com.softserve.edu.items.services.UserService;

@WebServlet({"/deleteMovie"})
public class DeleteMovieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MovieService movieService;
	UserService userService;
	
	public DeleteMovieServlet () {
		movieService = IocContainer.get().getMovieService();
		userService = IocContainer.get().getUserService();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session == null || session.getAttribute("login") == null || session.getAttribute("password") == null) {
			getServletConfig()
			.getServletContext()
			.getRequestDispatcher(ViewUrls.LOGIN_JSP.toString())
			.forward(request, response);
		} else {
			LoginDto loginDto = new LoginDto(session.getAttribute("login").toString(), 
					 session.getAttribute("password").toString());
			UsersDto user = userService.getUserDto(loginDto);
			//check role
			if (user.getRole().equals(Role.ROLE_ADMIN.toString())) {
				Long movieId = Long.parseLong(request.getParameter("movieId"));
				Boolean isDeleted = movieService.deleteMovieById(movieId);
				if (!isDeleted) {
					request.setAttribute("error", "Cannot delete this movie. Contact the developer.");
				} 
				response.sendRedirect(request.getContextPath()
						+ ControllerUrls.ADMIN_MOVIES_LIST.toString());
			} else {
				response.sendRedirect(request.getContextPath()
						+ ControllerUrls.MOVIES_LIST.toString());
			}
		}
	}
	
}