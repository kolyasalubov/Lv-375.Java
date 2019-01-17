package myArticles.edu.controllers.users;


import myArticles.edu.Services.UserService;
import myArticles.edu.container.IocContainer;
import myArticles.edu.controllers.ControllerUrls;
import myArticles.edu.controllers.ControllersConstant;
import myArticles.edu.controllers.ViewUrls;
import myArticles.edu.dto.LoginDto;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet({"/", "/login"})
public class UserLogin extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserService userService;
    private boolean isFirstStart;

    public UserLogin() {
        super();
        // TODO Auto-generated constructor stub
        userService = IocContainer.get().getUserService();
        isFirstStart = true;
    }

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        LoginDto loginDto = new LoginDto(request.getParameter(ControllersConstant.USERNAME.toString()), request.getParameter(ControllersConstant.PASSWORD.toString()));
        if (userService.checkValidLogin(loginDto)) {
            HttpSession session = request.getSession(true);
            session.setAttribute(ControllersConstant.LOGIN_DTO.toString(), loginDto);
            session.setMaxInactiveInterval(300000);
            Cookie cookie = new Cookie(ControllersConstant.SESSION_ID.toString(), session.getId());
            response.addCookie(cookie);
            request.isRequestedSessionIdFromCookie();
            response.sendRedirect(request.getContextPath() +
                    ControllerUrls.USER_ITEMS_SERVLET.toString());
        } else {
            request.setAttribute(ControllersConstant.ERROR.toString(), ControllersConstant.LOGIN_ERROR.toString());
            getServletConfig()
                    .getServletContext()
                    .getRequestDispatcher(ViewUrls.LOGIN_JSP.toString())
                    .forward(request, response);
        }
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        if (isFirstStart) {
            isFirstStart = false;
            request.setAttribute(ControllersConstant.LOGIN_URL.toString(),	// TODO
                    request.getRequestURL().toString());

        } else {
            getServletConfig()
                    .getServletContext()
                    .getRequestDispatcher(ViewUrls.LOGIN_JSP.toString())
                    .forward(request, response);
        }
        }

}
