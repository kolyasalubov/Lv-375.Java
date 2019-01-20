package myArticles.edu.controllers.common;

import myArticles.edu.Services.UserArticlesService;
import myArticles.edu.container.IocContainer;
import myArticles.edu.controllers.ControllerUrls;
import myArticles.edu.controllers.Security;
import myArticles.edu.controllers.ViewUrls;
import myArticles.edu.dto.ArticleDto;
import myArticles.edu.dto.LoginDto;
import myArticles.edu.dto.PageInfoDto;
import myArticles.edu.dto.UsersArticleDto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/userarticle")
public class UserArticles extends HttpServlet {
    private static final long serialVersionUID = 11L;
    private UserArticlesService userArticlesService;

    public UserArticles(){
        userArticlesService = IocContainer.get().getUserArticlesService();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String visibleArticle;
            visibleArticle = PageConfiguration.getVisibleArticle(request);
            int pageNumber = PageConfiguration.getPageNumber(request);
            request.setAttribute("pageNumber",
                    String.valueOf(pageNumber));
            System.out.println(pageNumber);
            System.out.println(visibleArticle);
            PageInfoDto pageInfoDto = new PageInfoDto(pageNumber, Integer.parseInt(visibleArticle));
            UsersArticleDto usersArticleDto = userArticlesService.getPageUsers(IocContainer.get()
                    .getUserService()
                    .getUserDto(((LoginDto)(request.getSession(false).getAttribute("loginDto")))), pageInfoDto);
            for(ArticleDto articleDto : usersArticleDto.getArticles()){
                System.out.println(articleDto.getName());
            }
            request.setAttribute("usersArticleDto", usersArticleDto);
            request.setAttribute("countArticles", usersArticleDto.getArticles().size());
            getServletConfig()
                    .getServletContext()
                    .getRequestDispatcher(ViewUrls.USER_ARTICLES_JSP.toString())
                    .forward(request, response);
        }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       doPost(request, response);
    }
}
