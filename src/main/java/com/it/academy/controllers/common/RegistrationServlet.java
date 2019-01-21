package com.it.academy.controllers.common;

import com.it.academy.common.ControllerUrls;
import com.it.academy.common.ObjContainer;
import com.it.academy.controllers.RequestValidator;
import com.it.academy.common.ViewUrls;
import com.it.academy.constants.UserConstants;
import com.it.academy.dto.UserDto;
import com.it.academy.service.UserService;

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
    private RequestValidator requestValidator;

    public RegistrationServlet() {
        super();
        userService = ObjContainer.getInstance().getUserService();
        requestValidator = ObjContainer.getInstance().getRequestValidator();
    }

    /**
     * Shows the registration form or the main page
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (requestValidator.isValid(request)) {
            response.sendRedirect(request.getContextPath()
                    + ControllerUrls.HOME_SERVLET.toString());
        } else {
            request.setAttribute(UserConstants.ON_SUBMIT.toString(), "Sign up");
            request.setAttribute(UserConstants.URL_TO_POST.toString(), "registration");
            getServletConfig()
                    .getServletContext()
                    .getRequestDispatcher(ViewUrls.USER_PROFILE_EDIT_JSP.toString())
                    .forward(request, response);
        }
    }


    /**
     * Signs up the user
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter(UserConstants.EMAIL.toString());
        String password = request.getParameter(UserConstants.PASSWORD.toString());
        String passwordRepeat = request.getParameter(UserConstants.PASSWORD_REPEAT.toString());

        if (password.equals(passwordRepeat)) {
            UserDto userDto = new UserDto(email, password,
                    request.getParameter(UserConstants.FIRST_NAME.toString()),
                    request.getParameter(UserConstants.LAST_NAME.toString()),
                    request.getParameter(UserConstants.POSITION.toString()),
                    request.getParameter(UserConstants.PHONE.toString()));

            if (userService.createUser(userDto)) {
                // To call doPost from LoginServlet --- to log in the user after registration
                getServletConfig()
                        .getServletContext()
                        .getRequestDispatcher(request.getContextPath()
                                + ControllerUrls.LOGIN_SERVLET.toString())
                        .forward(request, response);
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
