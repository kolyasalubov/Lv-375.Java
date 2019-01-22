package com.softserve.edu.items.controllers.users;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.softserve.edu.container.IocContainer;
import com.softserve.edu.items.controllers.ControllerUrl;
import com.softserve.edu.items.controllers.SessionManager;
import com.softserve.edu.items.controllers.ViewUrls;
import com.softserve.edu.items.dto.UserDto;
import com.softserve.edu.items.entity.FieldNames;
import com.softserve.edu.items.services.AllUsersService;
import com.softserve.edu.items.services.UserService;

/**
 * Servlet implementation class UserEditServlet
 */
@WebServlet(ControllerUrl.USER_UPDATE_SERVLET)
public class UserUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserService userService;
    private AllUsersService allUserService;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserUpdateServlet() {
	userService = IocContainer.get().getUserService();
	allUserService = IocContainer.get().getAllUserService();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {

	UserDto userDto;

	// Validation session and getting any changes
	if (SessionManager.checkSession(request)) {
	    userDto = userService.getUserDto(request.getParameter(FieldNames.LOGIN.toString()));

	    if (request.getParameter(FieldNames.IS_ACTIVE.toString()) != null) {
		userDto.setIsActive(userDto.getIsActive() == 0 ? 1 : 0);
	    }

	    if (request.getParameter(FieldNames.IS_ADMIN.toString()) != null) {
		userDto.setIsAdmin(userDto.getIsAdmin() == 0 ? 1 : 0);
	    }

	    userService.setUserDto(userDto);
	    // Setting allUsersDto to show changes in real time
	    getServletConfig().getServletContext().setAttribute("allUsersDto", allUserService.getAllUsersDto());
	    getServletConfig().getServletContext().getRequestDispatcher(ViewUrls.ALL_USERS_JSP.toString())
		    .forward(request, response);
	} else {
	    getServletConfig().getServletContext().getRequestDispatcher(ViewUrls.LOGIN_JSP.toString()).forward(request,
		    response);
	}
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	doGet(request, response);
    }

}
