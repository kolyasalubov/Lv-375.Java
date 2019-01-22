package myArticles.edu.controllers.users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import myArticles.edu.Services.UserService;
import myArticles.edu.container.IocContainer;
import myArticles.edu.controllers.ControllerUrls;
import myArticles.edu.controllers.ControllersConstant;
import myArticles.edu.controllers.Security;
import myArticles.edu.controllers.ViewUrls;
import myArticles.edu.dto.UserDto;

import java.io.IOException;

/**
 * This Servlet is responsible for creating new user
 */
@WebServlet("/usercreate")
public class UserCreate extends HttpServlet {
    private static final long serialVersionUID = 2L;
    private UserService userService;

    public UserCreate() {
        userService = IocContainer.get().getUserService();
    }

    /**
     * In this method we get all info about user from request
     * And try to add new user into Database , or add info about error in page, if we get it
     * @param request - HttpRequest
     * @param response - HttpResponse
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (Security.checkCorrectData(request)) {
            UserDto userDto = new UserDto(request.getParameter(ControllersConstant.USERNAME.toString()),
                    request.getParameter(ControllersConstant.PASSWORD.toString()),
                    request.getParameter(ControllersConstant.EMAIL.toString()),
                    Boolean.valueOf(request.getParameter(ControllersConstant.IS_ADMIN.toString())),
                    Boolean.valueOf(request.getParameter(ControllersConstant.IS_BLOCK.toString())));

            if (userService.registerUser(userDto)) {
                response.sendRedirect(request.getContextPath() + ControllerUrls.LOGIN_SERVLET.toString());
            } else {
                request.setAttribute(ControllersConstant.ERROR.toString(), ControllersConstant.REGISTER_ERROR.toString());
                getServletConfig()
                        .getServletContext()
                        .getRequestDispatcher(ViewUrls.USER_REGISTER_JSP.toString())
                        .forward(request, response);
            }
        } else {
            request.setAttribute(ControllersConstant.ERROR.toString(), ControllersConstant.PASSWORD_ERROR.toString());
            getServletConfig()
                    .getServletContext()
                    .getRequestDispatcher(ViewUrls.USER_REGISTER_JSP.toString())
                    .forward(request, response);
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletConfig().
                getServletContext()
                .getRequestDispatcher(ViewUrls.USER_REGISTER_JSP.toString())
                .forward(request, response);
    }
}
