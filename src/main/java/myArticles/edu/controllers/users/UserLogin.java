package myArticles.edu.controllers.users;


import myArticles.edu.Services.DataBaseConnectionService;
import myArticles.edu.Services.UserService;
import myArticles.edu.container.IocContainer;
import myArticles.edu.controllers.ControllerUrls;
import myArticles.edu.controllers.ControllersConstant;
import myArticles.edu.controllers.ViewUrls;
import myArticles.edu.dto.LoginDto;
import myArticles.edu.dto.UserDto;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        LoginDto loginDto = new LoginDto(request.getParameter(ControllersConstant.USERNAME.toString()), request.getParameter(ControllersConstant.PASSWORD.toString()));
        UserDto userDto = userService.getUserDto(loginDto);
        if (userService.checkValidLogin(loginDto)) {
            HttpSession session = request.getSession(true);
            session.setAttribute(ControllersConstant.LOGIN_DTO.toString(), loginDto);
            session.setMaxInactiveInterval(300000);
            Cookie cookie = new Cookie(ControllersConstant.SESSION_ID.toString(), session.getId());

            response.addCookie(cookie);
            //response.sendRedirect(request.getContextPath() +
            //ControllerUrls.USER_ITEMS_SERVLET.toString());
            if(userDto.isAdmin()) {
                getServletConfig()
                        .getServletContext()
                        .getRequestDispatcher(ControllerUrls.ALL_USER_SERVLER.toString())//TODO Change to Main page
                        .forward(request, response);
            }
            else {
                getServletConfig()
                        .getServletContext()
                        .getRequestDispatcher(ControllerUrls.USER_ARTICLES_SERVLET.toString())//TODO Change to Main page
                        .forward(request, response);
            }
        } else {
            request.setAttribute(ControllersConstant.ERROR.toString(), ControllersConstant.LOGIN_ERROR.toString());
            getServletConfig()
                    .getServletContext()
                    .getRequestDispatcher(ViewUrls.LOGIN_JSP.toString())
                    .forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        if (isFirstStart) {
            dataBaseConnectionService.createDataTable();
            isFirstStart = false;
        }
        System.out.println("NUSYA BAKA");
        getServletConfig()
                .getServletContext()
                .getRequestDispatcher(ViewUrls.LOGIN_JSP.toString())
                .forward(request, response);
    }
}


