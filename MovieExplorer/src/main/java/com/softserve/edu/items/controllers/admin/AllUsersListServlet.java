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
import com.softserve.edu.items.dto.UsersDto;
import com.softserve.edu.items.dto.UsersListDto;
import com.softserve.edu.items.entity.Role;
import com.softserve.edu.items.services.UserService;

@WebServlet({"/allUsersList"})
public class AllUsersListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService;
	
	public AllUsersListServlet() {
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
			LoginDto loginDto = new LoginDto(session.getAttribute("login").toString(), 
					 session.getAttribute("password").toString());
			UsersDto user = userService.getUserDto(loginDto);
			if (user.getRole().equals(Role.ROLE_ADMIN.toString())) {
				UsersListDto usersListDto = new UsersListDto(userService.getUsersList("email"));
				request.setAttribute("usersListDto", usersListDto);
				//dropdown
				List<Dropdown> dropdownValues = new ArrayList<>();
				dropdownValues.add(new Dropdown("Log Out", ControllerUrls.LOGOUT_SERVLET.toString()));
				dropdownValues.add(new Dropdown("Add movie", ControllerUrls.ADD_MOVIE_SERVLET.toString()));
				dropdownValues.add(new Dropdown("Edit/Delete movie", ControllerUrls.ADMIN_MOVIES_LIST.toString()));
				dropdownValues.add(new Dropdown("All users list", ControllerUrls.ALL_USERS_LIST_SERVLET.toString()));
				request.setAttribute("dropdownValues", dropdownValues);
				getServletConfig()
				.getServletContext()
				.getRequestDispatcher(ViewUrls.USERS_LIST_JSP.toString()).forward(request, response);
			} else {
				response.sendRedirect(request.getContextPath()
						+ ControllerUrls.MOVIES_LIST.toString());
			}
		}
	}		

}