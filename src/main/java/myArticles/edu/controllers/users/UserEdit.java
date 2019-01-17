package myArticles.edu.controllers.users;


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

@WebServlet("/useredit")
public class UserEdit extends HttpServlet {
    private static final long serialVersionUID = 3L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            if(request.isRequestedSessionIdFromCookie() && request.isRequestedSessionIdValid()){
                UserDto userDto = (UserDto)request.getSession().getAttribute(ControllersConstant.USER_DTO.toString());
                //TODO Create views make
                getServletConfig()
                        .getServletContext()
                        .getRequestDispatcher(ViewUrls.USER_PROFILE_JSP.toString())
                        .forward(request, response);
            }
            else{
                Cookie cookie = new Cookie("name", "");
                cookie.setMaxAge(0);
                response.addCookie(cookie);
                response.sendRedirect(request.getContextPath()+ViewUrls.LOGIN_JSP.toString());
            }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
