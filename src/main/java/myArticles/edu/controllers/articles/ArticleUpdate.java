package myArticles.edu.controllers.articles;

import myArticles.edu.Services.ArticleService;
import myArticles.edu.Services.UserService;
import myArticles.edu.container.IocContainer;
import myArticles.edu.controllers.Security;
import myArticles.edu.controllers.ViewUrls;
import myArticles.edu.dto.ArticleDto;
import myArticles.edu.dto.UserDto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/articleupdate")
public class ArticleUpdate extends HttpServlet {
    private static final long serialVersionUID = 8L;
    private ArticleService articleService;
    private UserService userService;
    private Security security;

    ArticleUpdate(){
        articleService = IocContainer.get().getArticleService();
        userService = IocContainer.get().getUserService();
        security = IocContainer.get().getSecurity();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.isRequestedSessionIdFromCookie() && request.isRequestedSessionIdValid()) {
            UserDto userDto = (UserDto)request.getSession().getAttribute("userDto");
            Long userId = userService.getIdUserByLogin(userDto);
            ArticleDto articleDto = new ArticleDto(
                    request.getParameter("Name"),
                    request.getParameter("Description"),
                    request.getParameter("Url"),
                    userId);

            if(articleService.updateArticles(articleDto)){
                getServletConfig()
                        .getServletContext()
                        .getRequestDispatcher(ViewUrls.USER_ARTICLES_JSP.toString())
                        .forward(request, response);
            }
            else{
                //TODO error
            }

        }
        else{
            security.endSession(response).sendRedirect(ViewUrls.LOGIN_JSP.toString());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //TODO CREATE LOGOUT
    }
}
