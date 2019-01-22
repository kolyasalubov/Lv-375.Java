package com.it.academy.controllers.admin;

import com.it.academy.common.ControllerUrls;
import com.it.academy.common.ObjContainer;
import com.it.academy.common.ViewUrls;
import com.it.academy.constants.ErrorConstants;
import com.it.academy.constants.PaginationConstants;
import com.it.academy.constants.UserConstants;
import com.it.academy.controllers.RequestValidator;
import com.it.academy.dto.CollectionDto;
import com.it.academy.dto.LoginDto;
import com.it.academy.dto.RoomDto;
import com.it.academy.dto.UserDto;
import com.it.academy.service.PaginationService;
import com.it.academy.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * RoomServlet configures archive of bookings in particular room
 */
@WebServlet({"/admin-users"})
public class AdminUsersServlet extends HttpServlet {
    private static final long serialVersionUID = 15L;
    private UserService userService;
    private PaginationService<UserDto> paginationService;
    private RequestValidator requestValidator;

    public AdminUsersServlet() {
        super();
        userService = ObjContainer.getInstance().getUserService();
        requestValidator = ObjContainer.getInstance().getRequestValidator();
        paginationService =  ObjContainer.getInstance().getPaginationServices().get(PaginationConstants.USER_PAGE.toString());
    }

    /**
     * Shows the bookings in particular room
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (requestValidator.isValid(request)) {
            if(requestValidator.isAdmin(request)) {

                CollectionDto<UserDto> users = userService.getUserCollectionDto();

                String pageOffset = request.getParameter(PaginationConstants.PAGE_OFFSET.toString());
                String page = request.getParameter(PaginationConstants.PAGE.toString());

                users = paginationService.updateCollection(users, pageOffset, page);
                request.setAttribute(UserConstants.USERS.toString(), users);

                getServletConfig()
                        .getServletContext()
                        .getRequestDispatcher(ViewUrls.ADMIN_USERS_JSP.toString())
                        .forward(request, response);
            } else {
                request.setAttribute(ErrorConstants.ERROR.toString(), ErrorConstants.NOT_THE_ADMIN.toString());
                getServletConfig()
                        .getServletContext()
                        .getRequestDispatcher(request.getContextPath()
                                + ControllerUrls.HOME_SERVLET)
                        .forward(request, response);
            }
        } else {
            response.sendRedirect(request.getContextPath()
                    + ControllerUrls.LOGIN_SERVLET);
        }
    }


    /**
     * Signs up the user
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter(UserConstants.ID.toString());
        String isAdmin = request.getParameter(UserConstants.IS_ADMIN.toString());
        String isBlocked = request.getParameter(UserConstants.IS_BLOCKED.toString());

        UserDto userDto = new UserDto();
        userDto.setIdUser(id);

            userDto.setIsAdmin(isAdmin);
            userService.adminToUser(userDto);

            userDto.setIsBlocked(isBlocked);
            userService.blockToUser(userDto);

        response.sendRedirect(request.getContextPath()
                + ControllerUrls.ADMIN_USERS_SERVLET);
    }


}
