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
    private Security security;

    public UserEdit(){
        super();
        security = IocContainer.get().getSecurity();
        userService = IocContainer.get().getUserService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            if(Security.isActiveSession(request, response)){
                UserDto userDto = userService.getUserDto((LoginDto)request.getSession().getAttribute(ControllersConstant.LOGIN_DTO.toString()));
                request.setAttribute(ControllersConstant.USERNAME.toString(), userDto.getUserName());
                request.setAttribute(ControllersConstant.PASSWORD.toString(), userDto.getPassword());
                request.setAttribute(ControllersConstant.EMAIL.toString(), userDto.getEmail());
                request.setAttribute(ControllersConstant.IS_ADMIN.toString(), Boolean.toString(userDto.isAdmin()));
                request.setAttribute(ControllersConstant.IS_BLOCK.toString(), Boolean.toString(userDto.isBlock()));
                getServletConfig()
                        .getServletContext()
                        .getRequestDispatcher(ViewUrls.USER_PROFILE_JSP.toString())
                        .forward(request, response);
            }
            else{
                System.out.println("NOU");
                LoginDto loginDto = (LoginDto)request.getSession().getAttribute(ControllersConstant.LOGIN_DTO.toString());
                System.out.println(loginDto.getUserName());
                System.out.println(loginDto.getPassword());
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
