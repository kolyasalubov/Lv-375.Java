package com.it.academy.controllers.common;

import com.it.academy.common.ControllerUrls;
import com.it.academy.common.ObjContainer;
import com.it.academy.controllers.RequestValidator;
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
 * LoginServlet configures the first page
 */
@WebServlet({"/", "/login"})  //  ROOT_SERVLET, LOGIN_SERVLET
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private UserService userService;

    public LoginServlet() {
        super();
        userService = ObjContainer.getInstance().getUserService();
    }

    /**
     * Shows the login form or the home page
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext context = getServletConfig().getServletContext();
        if (RequestValidator.isValid(request)) {
            response.sendRedirect(request.getContextPath()
                    + ControllerUrls.HOME_SERVLET.toString());

        } else {
            context.getRequestDispatcher(ViewUrls.LOGIN_JSP.toString())
                    .forward(request, response);
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
            if(!userService.isBlocked(loginDto)) {
                // Create session
                HttpSession session = request.getSession(true);
                session.setAttribute(UserConstants.LOGIN_DTO.toString(), loginDto);

                response.sendRedirect(request.getContextPath()
                        + ControllerUrls.HOME_SERVLET.toString());

            } else {
                request.setAttribute("error", "You are blocked user!");
            }
        } else {
            request.setAttribute("error", "Bad Login or Password!");
        }

        // Show Error Validator
        if(request.getAttribute("error") != null){
            getServletConfig()
                    .getServletContext()
                    .getRequestDispatcher(ViewUrls.LOGIN_JSP.toString())
                    .forward(request, response);
        }

    }
}
