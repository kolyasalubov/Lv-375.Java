package com.it.academy.controllers;

import com.it.academy.common.ControllerUrls;
import com.it.academy.common.ObjContainer;
import com.it.academy.common.ViewUrls;
import com.it.academy.constants.UserConstants;
import com.it.academy.dto.LoginDto;
import com.it.academy.service.UserService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * LoginServlet configures login page
 */
@WebServlet({"/", "/login"})  //  ROOT_SERVLET,  LOGIN_SERVLET
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private UserService userService;

    public LoginServlet() {
        super();
        userService = ObjContainer.getInstance().getUserService();
    }

    /**
     * Shows the login form
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext context = getServletConfig().getServletContext();
        System.out.println(request.getSession(false) == null);
        if (request.isRequestedSessionIdFromCookie()
                && request.isRequestedSessionIdValid()
                && request.getSession().getAttribute(UserConstants.LOGIN_DTO.toString()) != null) {
//            context.getRequestDispatcher(ViewUrls.HOME_JSP.toString())
//                    .include(request, response);
            response.sendRedirect(request.getContextPath()
                    + ControllerUrls.HOME_SERVLET.toString());

        } else {
            context.getRequestDispatcher(ViewUrls.LOGIN_JSP.toString())
                    .include(request, response);
        }

//        PrintWriter out = new PrintWriter(response.getWriter());
//        out.println("This a test!!!");
    }

    /**
     * Signs in the user
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LoginDto loginDto = new LoginDto(
                request.getParameter(UserConstants.EMAIL.toString()),
                request.getParameter(UserConstants.PASSWORD.toString()));

        if (userService.isValid(loginDto)){
            // Create session
            HttpSession session = request.getSession(true);
            session.setAttribute(UserConstants.LOGIN_DTO.toString(), loginDto);

            response.sendRedirect(request.getContextPath()
                    + ControllerUrls.HOME_SERVLET.toString());

        } else {
            // Show Error Validator
            request.setAttribute("error", "Bad Login or Password");
            getServletConfig()
                    .getServletContext()
                    .getRequestDispatcher(ViewUrls.LOGIN_JSP.toString())
                    .forward(request, response);
        }
    }
}
