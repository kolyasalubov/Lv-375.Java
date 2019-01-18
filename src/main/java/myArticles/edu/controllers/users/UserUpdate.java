package myArticles.edu.controllers.users;


import myArticles.edu.Services.UserService;
import myArticles.edu.container.IocContainer;
import myArticles.edu.controllers.ControllersConstant;
import myArticles.edu.controllers.ViewUrls;
import myArticles.edu.dto.UserDto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/userupdate")
public class UserUpdate extends HttpServlet {
    private static final long serialVersionUID = 4L;
    private UserService userService;

    public UserUpdate(){
        userService = IocContainer.get().getUserService();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.isRequestedSessionIdFromCookie() && request.isRequestedSessionIdValid()) {
            UserDto userDto = new UserDto(request.getParameter(ControllersConstant.USERNAME.toString()),
                    request.getParameter(ControllersConstant.PASSWORD.toString()),
                    request.getParameter(ControllersConstant.EMAIL.toString()),
                    Boolean.valueOf(request.getParameter(ControllersConstant.IS_ADMIN.toString())),
                    Boolean.valueOf(request.getParameter(ControllersConstant.IS_BLOCK.toString())));

            if(userService.updateUser(userDto)){
                request.getSession().setAttribute(ControllersConstant.USER_DTO.toString(), userDto);
                getServletConfig()
                        .getServletContext()
                        .getRequestDispatcher(ViewUrls.USER_ARTICLES_JSP.toString())
                        .forward(request, response);
            }
            else{
                //TODO error to update
            }
        }
        else{
            Cookie cookie = new Cookie("name", "");
            cookie.setMaxAge(0);
            response.addCookie(cookie);
            response.sendRedirect(request.getContextPath()+ ViewUrls.LOGIN_JSP.toString());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
