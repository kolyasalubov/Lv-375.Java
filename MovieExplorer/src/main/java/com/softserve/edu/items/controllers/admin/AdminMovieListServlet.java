package com.softserve.edu.items.controllers.admin;

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
import com.softserve.edu.items.dto.MoviesListDto;
import com.softserve.edu.items.dto.UsersDto;
import com.softserve.edu.items.entity.Role;
import com.softserve.edu.items.services.MovieService;
import com.softserve.edu.items.services.UserService;

@WebServlet({"/adminMovieList"})
public class AdminMovieListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MovieService movieService;	
	private UserService userService;
	
	public AdminMovieListServlet() {
		this.movieService = IocContainer.get().getMovieService();
		this.userService = IocContainer.get().getUserService();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session == null || session.getAttribute("login") == null || session.getAttribute("password") == null) {
			System.out.println("session == null");
			getServletConfig()
			.getServletContext()
			.getRequestDispatcher(ViewUrls.LOGIN_JSP.toString())
			.forward(request, response);
		} else {
			//Check user role
			LoginDto loginDto = new LoginDto(session.getAttribute("login").toString(), 
											 session.getAttribute("password").toString());
			UsersDto user = userService.getUserDto(loginDto);
			
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
			
			//pagination
			int currentPage = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));
			int pageOffset = request.getParameter("pageOffset") == null ? 3 : Integer.parseInt(request.getParameter("pageOffset"));
			List<MovieDto> movies = movieService.getMoviesList(pageOffset * currentPage - pageOffset, pageOffset);
			int moviesCount = movieService.getMoviesCount();	        
	        int nOfPages = moviesCount / pageOffset;
	        if (moviesCount % pageOffset != 0) {
	        	nOfPages++;
	        }
	        MoviesListDto moviesListDto = new MoviesListDto(movies, currentPage, nOfPages, pageOffset);
	        
			//for navbar
			UsersDto userDto = userService.getUserDto(loginDto);
			
			request.setAttribute("moviesListDto", moviesListDto);
			request.setAttribute("userDto", userDto);
			getServletConfig()
				.getServletContext()
				.getRequestDispatcher(ViewUrls.ADMIN_MOVIES_LIST_JSP.toString()).forward(request, response);
		}
	}	

}

