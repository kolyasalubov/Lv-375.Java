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

@WebServlet({"/addMovie"})
public class AddMovieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MovieService movieService;
	UserService userService;
	
	public AddMovieServlet () {
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
			LoginDto loginDto = new LoginDto(
				session.getAttribute("login").toString(), 
				session.getAttribute("password").toString()
			);
			UsersDto user = userService.getUserDto(loginDto);
			if(user.getRole().equals(Role.ROLE_ADMIN.toString())) {
				getServletConfig()
				.getServletContext()
				.getRequestDispatcher(ViewUrls.ADD_MOVIE_JSP.toString()).forward(request, response);
			} else {
				response.sendRedirect(request.getContextPath()
						+ ControllerUrls.MOVIES_LIST.toString());
			}
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		MovieDto movieDto = new MovieDto(request.getParameter("title"),
										 request.getParameter("imdbMovieID"),
										 request.getParameter("information"),
										 request.getParameter("posterUrl")
				);
		boolean isCreated = movieService.addMovie(movieDto);
		if(isCreated) {
			response.sendRedirect(request.getContextPath()
					+ ControllerUrls.MOVIES_LIST.toString());
		} else {
			request.setAttribute("error", "Cannot create movie with such input");
			getServletConfig()
			.getServletContext()
			.getRequestDispatcher(ViewUrls.ADD_MOVIE_JSP.toString()).forward(request, response);
		}
	}
	
}
