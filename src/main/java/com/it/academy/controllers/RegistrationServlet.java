package com.it.academy.controllers;

import com.it.academy.common.ControllerUrls;
import com.it.academy.common.ObjContainer;
import com.it.academy.common.ViewUrls;
import com.it.academy.constants.UserConstants;
import com.it.academy.dto.UserDto;
import com.it.academy.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;


/**
 * RegistrationServlet configures registration page
 */
@WebServlet({"/registration"})  //  REGISTRATION_SERVLET
public class RegistrationServlet extends HttpServlet {

    private static final long serialVersionUID = 2L;
    private UserService userService;

    public RegistrationServlet() {
        super();
        userService = ObjContainer.getInstance().getUserService();
    }

    /**
     * Shows the registration form
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext context = getServletConfig().getServletContext();
        if (request.isRequestedSessionIdFromCookie()
                && request.isRequestedSessionIdValid()
                && request.getSession().getAttribute(UserConstants.LOGIN_DTO.toString()) != null) {
//            context.getRequestDispatcher(ViewUrls.HOME_JSP.toString())
//                    .include(request, response);
            response.sendRedirect(request.getContextPath()
                    + ControllerUrls.HOME_SERVLET.toString());

        } else {
            context.getRequestDispatcher(ViewUrls.USER_PROFILE_EDIT_JSP.toString())
                    .forward(request, response);
        }
    }


    /**
     * Sign ups the user
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter(UserConstants.EMAIL.toString());
        String password = request.getParameter(UserConstants.PASSWORD.toString());
        String passwordRepeat = request.getParameter(UserConstants.PASSWORD_REPEAT.toString());

        RequestDispatcher toRegistration = getServletConfig()
                .getServletContext()
                .getRequestDispatcher(ViewUrls.USER_PROFILE_EDIT_JSP.toString());

        if (password.equals(passwordRepeat)) {
            UserDto userDto = new UserDto(email, password,
                    request.getParameter(UserConstants.FIRST_NAME.toString()),
                    request.getParameter(UserConstants.LAST_NAME.toString()),
                    request.getParameter(UserConstants.POSITION.toString()),
                    request.getParameter(UserConstants.PHONE.toString()));

            if (userService.createUser(userDto)) {
                getServletConfig()
                    .getServletContext()
                    .getRequestDispatcher(ControllerUrls.LOGIN_SERVLET.toString())
                    .forward(request, response);
            } else {
                // Show Error Validator
                request.setAttribute("error", "This Email already exists");
                toRegistration.forward(request, response);
            }
        } else {
            request.setAttribute("error", "Password did not match");
            toRegistration.forward(request, response);
        }
    }
}
