package myArticles.edu.controllers.articles;

import myArticles.edu.Services.ArticleService;
import myArticles.edu.Services.UserService;
import myArticles.edu.container.IocContainer;
import myArticles.edu.controllers.ControllerUrls;
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


     public ArticleUpdate(){
        articleService = IocContainer.get().getArticleService();
        userService = IocContainer.get().getUserService();

    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(Security.isActiveSession(request, response)) {
            ArticleDto articleDto = new ArticleDto(
                    request.getParameter("name"),
                    request.getParameter("description"),
                    request.getParameter("url"),
                    Long.parseLong(request.getParameter("userId")));
            System.out.println(articleDto.getName()+" " + articleDto.getUserId());
            if(articleService.updateArticles(articleDto)){
                getServletConfig()
                        .getServletContext()
                        .getRequestDispatcher(ControllerUrls.USER_ARTICLES_SERVLET.toString())
                        .forward(request, response);
            }
        }
        else{
            Security.endSession(response);
            getServletConfig()
                    .getServletContext()
                    .getRequestDispatcher(ViewUrls.LOGIN_JSP.toString())
                    .forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //TODO CREATE LOGOUT
    }
}
