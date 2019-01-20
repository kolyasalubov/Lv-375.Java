package myArticles.edu.controllers.articles;

import myArticles.edu.Services.ArticleService;
import myArticles.edu.Services.UserService;
import myArticles.edu.container.IocContainer;
import myArticles.edu.controllers.Security;
import myArticles.edu.controllers.ViewUrls;
import myArticles.edu.dto.ArticleDto;
import myArticles.edu.dto.LoginDto;
import myArticles.edu.dto.UserDto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/articleedit")
public class ArticleEdit extends HttpServlet {
    private static final long serialVersionUID = 7L;
    private ArticleService articleService;


    public ArticleEdit(){
        articleService = IocContainer.get().getArticleService();

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(Security.isActiveSession(request, response)) {
            System.out.println(request.getQueryString()+"555");
            ArticleDto articleDto = new ArticleDto(
                    request.getQueryString().substring(5),
                    "","",0L
                    );
            articleDto = articleService.getFullInfo(articleDto);
            request.getSession().setAttribute("articleDto",  articleDto);
            getServletConfig()
                    .getServletContext()
                    .getRequestDispatcher(ViewUrls.ARTICLES_PROFILE_JSP.toString())
                    .forward(request, response);
        }
        else{
            Security.endSession(response);
            getServletConfig()
                    .getServletContext()
                    .getRequestDispatcher(ViewUrls.LOGIN_JSP.toString())
                    .forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
