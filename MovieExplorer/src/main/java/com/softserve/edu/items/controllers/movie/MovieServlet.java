package com.softserve.edu.items.controllers.movie;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.softserve.edu.items.Dropdown;
import com.softserve.edu.items.IocContainer;
import com.softserve.edu.items.controllers.ControllerUrls;
import com.softserve.edu.items.controllers.ViewUrls;
import com.softserve.edu.items.dto.LoginDto;
import com.softserve.edu.items.dto.MovieDto;
import com.softserve.edu.items.dto.UsersDto;
import com.softserve.edu.items.entity.Role;
import com.softserve.edu.items.services.UserService;
import com.softserve.edu.items.services.UsersMovieService;

@WebServlet ({"/movie"})
public class MovieServlet extends HttpServlet {
	static final long serialVersionUID = 1L;
	private UserService userService;
	private UsersMovieService usersMovieService;
	
	public MovieServlet() {
		this.userService = IocContainer.get().getUserService();
		this.usersMovieService = IocContainer.get().getUsersMovieService();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session == null || session.getAttribute("login") == null || session.getAttribute("password") == null) {
			getServletConfig()
			.getServletContext()
			.getRequestDispatcher(ViewUrls.LOGIN_JSP.toString())
			.forward(request, response);
		} else {
			//Check user role
			LoginDto login = new LoginDto(session.getAttribute("login").toString(), 
					 session.getAttribute("password").toString());
			UsersDto user = userService.getUserDto(login);
			//add dropdown
			List<Dropdown> dropdownValues = new ArrayList<>();
			dropdownValues.add(new Dropdown("Log Out", ControllerUrls.LOGOUT_SERVLET.toString()));
			dropdownValues.add(new Dropdown("Watch later", ControllerUrls.FAVOURITE_MOVIES_LIST.toString()));
			if (user.getRole().equals(Role.ROLE_ADMIN.toString())) {
				dropdownValues.add(new Dropdown("Add movie", ControllerUrls.ADD_MOVIE_SERVLET.toString()));
				dropdownValues.add(new Dropdown("Edit/Delete movie", ControllerUrls.ADMIN_MOVIES_LIST.toString()));
				dropdownValues.add(new Dropdown("All users list", ControllerUrls.ALL_USERS_LIST_SERVLET.toString()));
			}
				request.setAttribute("dropdownValues", dropdownValues);
			LoginDto loginDto = new LoginDto(session.getAttribute("login").toString(), 
											 session.getAttribute("password").toString());
			Long userId = userService.getIdUserByLogin(loginDto);
			Long movieId = Long.parseLong(request.getParameter("movieId"));
			MovieDto movie = usersMovieService.getMovieDto(userId, movieId);
			System.out.println("doGet movie: " + movie.toString());
			request.setAttribute("movie", movie);
			getServletConfig()
			.getServletContext()
			.getRequestDispatcher(ViewUrls.MOVIE_PROFILE_JSP.toString()).forward(request, response);
		}		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session == null || session.getAttribute("login") == null || session.getAttribute("password") == null) {
			getServletConfig()
			.getServletContext()
			.getRequestDispatcher(ViewUrls.LOGIN_JSP.toString())
			.forward(request, response);
		} else {
			LoginDto loginDto = new LoginDto(session.getAttribute("login").toString(), 
											 session.getAttribute("password").toString());
			Long usersId = userService.getIdUserByLogin(loginDto);
			Long movieId = Long.parseLong(request.getParameter("movieId"));
			Boolean result = usersMovieService.addToFavorites(usersId, movieId);
			if(!result) {
				request.setAttribute("error", "Cannot add to favorite. Contact the developer.");
			}
			response.sendRedirect(request.getContextPath()
					+ ControllerUrls.MOVIE_PROFILE.toString() + "?movieId=" + movieId);
		}	
	}
	
	

}
