package myArticles.edu.controllers.users;


import myArticles.edu.Services.UserService;
import myArticles.edu.container.IocContainer;
import myArticles.edu.controllers.ControllerUrls;
import myArticles.edu.controllers.ControllersConstant;
import myArticles.edu.controllers.Security;
import myArticles.edu.controllers.ViewUrls;
import myArticles.edu.dto.LoginDto;
import myArticles.edu.dto.UserDto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/useredit")
public class UserEdit extends HttpServlet {
    private static final long serialVersionUID = 3L;
    private UserService userService;


    public UserEdit() {
        super();

        userService = IocContainer.get().getUserService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (Security.isActiveSession(request, response)) {
            UserDto userDto = userService.getUserDto((LoginDto) request.getSession().getAttribute(ControllersConstant.LOGIN_DTO.toString()));
            request.setAttribute(ControllersConstant.USER_DTO.toString(), userDto);

            request.setAttribute(ControllersConstant.CANCEL.toString(),
                    userDto.isAdmin() ? ControllerUrls.ALL_USER_SERVLER.toString()
                            : ControllerUrls.USER_ARTICLES_SERVLET.toString());

            getServletConfig()
                    .getServletContext()
                    .getRequestDispatcher(ViewUrls.USER_PROFILE_JSP.toString())
                    .forward(request, response);
        } else {
            Security.endSession(response);
            getServletConfig()
                    .getServletContext()
                    .getRequestDispatcher(ViewUrls.LOGIN_JSP.toString())
                    .forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
