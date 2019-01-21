package myArticles.edu.controllers.admin;

import myArticles.edu.Services.UserService;
import myArticles.edu.container.IocContainer;
import myArticles.edu.controllers.ControllerUrls;
import myArticles.edu.controllers.ControllersConstant;
import myArticles.edu.controllers.Security;
import myArticles.edu.controllers.ViewUrls;
import myArticles.edu.dto.UserDto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/changeadminstatus")
public class ChangeAdminStatus extends HttpServlet {
    private static final long serialVersionUID = 15L;
    private UserService userService;

    public ChangeAdminStatus() {
        userService = IocContainer.get().getUserService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (Security.isActiveSession(request, response)) {
            String UserName = request.getParameter(ControllersConstant.USERNAME.toString());
            UserDto userDto = new UserDto(UserName, "", "", false, false);
            userService.changeAdminStatus(userDto);
            getServletConfig()
                    .getServletContext()
                    .getRequestDispatcher(ControllerUrls.ALL_USER_SERVLER.toString())
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
