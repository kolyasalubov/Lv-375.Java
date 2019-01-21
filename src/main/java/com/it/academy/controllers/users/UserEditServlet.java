package com.it.academy.controllers.users;

import com.it.academy.common.ControllerUrls;
import com.it.academy.common.ObjContainer;
import com.it.academy.common.ViewUrls;
import com.it.academy.constants.BookingConstants;
import com.it.academy.constants.UserConstants;
import com.it.academy.controllers.RequestValidator;
import com.it.academy.dto.BookingRoomDto;
import com.it.academy.dto.LoginDto;
import com.it.academy.dto.UserDto;
import com.it.academy.service.BookingService;
import com.it.academy.service.RoomService;
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
@WebServlet({"/user-edit"})  //  ROOM_ARCHIVE_SERVLET
public class UserEditServlet extends HttpServlet{
    private static final long serialVersionUID = 13L;
    private UserService userService;
    private RequestValidator requestValidator;

    public UserEditServlet() {
        super();
        userService = ObjContainer.getInstance().getUserService();
        requestValidator = ObjContainer.getInstance().getRequestValidator();
    }

    /**
     * Shows the bookings in particular room
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(requestValidator.isValid(request)) {
            LoginDto loginDto = (LoginDto) request.getSession().getAttribute(UserConstants.LOGIN_DTO.toString());
            UserDto userDto = userService.getUserDto(loginDto);

            request.setAttribute(UserConstants.USER_DTO.toString(), userDto);
            request.setAttribute(UserConstants.ON_SUBMIT.toString(), "Save");
            request.setAttribute(UserConstants.URL_TO_POST.toString(), "user-edit");

            getServletConfig()
                    .getServletContext()
                    .getRequestDispatcher(ViewUrls.USER_PROFILE_EDIT_JSP.toString())
                    .forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath()
                    + ControllerUrls.LOGIN_SERVLET);
        }
    }


    /**
     * Signs up the user
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter(UserConstants.EMAIL.toString());
        String password = request.getParameter(UserConstants.PASSWORD.toString());
        String passwordRepeat = request.getParameter(UserConstants.PASSWORD_REPEAT.toString());

        UserDto userDto = new UserDto(email, password,
                request.getParameter(UserConstants.FIRST_NAME.toString()),
                request.getParameter(UserConstants.LAST_NAME.toString()),
                request.getParameter(UserConstants.POSITION.toString()),
                request.getParameter(UserConstants.PHONE.toString()));
        userDto.setIdUser(request.getParameter(UserConstants.ID.toString()));

        if (password.equals(passwordRepeat)) {
            if (userService.updateUser(userDto)) {
                response.sendRedirect(request.getContextPath()
                        + ControllerUrls.USER_SERVLET);
            } else {
                request.setAttribute("error", "This email already exists!");
            }
        } else {
            request.setAttribute("error", "Passwords do not match!");
        }

        // Show Error Validator
        if(request.getAttribute("error") != null){
            doGet(request, response);
        }
    }
}
