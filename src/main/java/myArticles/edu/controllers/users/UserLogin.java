package myArticles.edu.controllers.users;


import myArticles.edu.Services.DataBaseConnectionService;
import myArticles.edu.Services.UserService;
import myArticles.edu.container.IocContainer;
import myArticles.edu.controllers.ControllerUrls;
import myArticles.edu.controllers.ControllersConstant;
import myArticles.edu.controllers.ViewUrls;
import myArticles.edu.dto.LoginDto;
import myArticles.edu.dto.UserDto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * This Servlet is responsible to show for us login page,
 * get info about user,
 * check can he login, or send error
 *
 * And if its first start create table in database if they are exists
 */
@WebServlet({"/", "/login"})
public class UserLogin extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserService userService;
    private DataBaseConnectionService dataBaseConnectionService;
    private boolean isFirstStart;

    public UserLogin() {
        super();
        dataBaseConnectionService = IocContainer.get().getDataBaseConnectionService();
        userService = IocContainer.get().getUserService();
        isFirstStart = true;
    }

    /**
     * In this method we check user's info and Login him or send error
     * this method create new session for each user who login
     * @param request - HttpRequest
     * @param response - HttpResponse
     * @throws javax.servlet.ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        LoginDto loginDto = new LoginDto(request.getParameter(ControllersConstant.USERNAME.toString()), request.getParameter(ControllersConstant.PASSWORD.toString()));
        UserDto userDto = userService.getUserDto(loginDto);
        if (userService.checkValidLogin(loginDto)) {
            //Create a session, to check user in future
            HttpSession session = request.getSession(true);
            session.setAttribute(ControllersConstant.LOGIN_DTO.toString(), loginDto);
            session.setMaxInactiveInterval(300000);
            Cookie cookie = new Cookie(ControllersConstant.SESSION_ID.toString(), session.getId());

            response.addCookie(cookie);

            if (userDto.isAdmin()) {
                response.sendRedirect(request.getContextPath() + ControllerUrls.ALL_USER_SERVLER.toString());
            } else {
                response.sendRedirect(request.getContextPath() + ControllerUrls.USER_ARTICLES_SERVLET.toString());
            }
            //Send error if user cant Login
        } else {
            request.setAttribute(ControllersConstant.ERROR.toString(), ControllersConstant.LOGIN_ERROR.toString());
            getServletConfig()
                    .getServletContext()
                    .getRequestDispatcher(ViewUrls.LOGIN_JSP.toString())
                    .forward(request, response);
        }
    }

    /**
     * This method create all table, which we need and create first account - admin
     * @param request - HttpRequest
     * @param response - Httpresponse
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (isFirstStart) {
            dataBaseConnectionService.createDataTable();
            isFirstStart = false;
            UserDto userDto = new UserDto(ControllersConstant.ADMIN_USERNAME.toString(),
                    ControllersConstant.ADMIN_PASSWORD.toString(),
                    ControllersConstant.ADMIN_EMAIL.toString(),
                    true, false);
            userService.registerUser(userDto);
        }
        getServletConfig()
                .getServletContext()
                .getRequestDispatcher(ViewUrls.LOGIN_JSP.toString())
                .forward(request, response);
    }
}


