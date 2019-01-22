package myArticles.edu.controllers.articles;

import myArticles.edu.Services.ArticleService;
import myArticles.edu.Services.UserService;
import myArticles.edu.container.IocContainer;
import myArticles.edu.controllers.ControllersConstant;
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

/**
 * This class start working when user press button "Edit Article"
 * We get all info about article from Database and show it into page
 */
@WebServlet("/articleedit")
public class ArticleEdit extends HttpServlet {
    private static final long serialVersionUID = 7L;
    private ArticleService articleService;


    public ArticleEdit() {
        articleService = IocContainer.get().getArticleService();

    }

    /**
     * Get all info about user and set it into request
     * @param request - HttpRequest
     * @param response - HttpResponse
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (Security.isActiveSession(request, response)) {
            ArticleDto articleDto = new ArticleDto(
                    request.getParameter(ControllersConstant.ARTICLE_NAME.toString()),
                    "", "", 0L
            );
            articleDto = articleService.getFullInfo(articleDto);
            request.getSession().setAttribute("articleDto", articleDto);
            getServletConfig()
                    .getServletContext()
                    .getRequestDispatcher(ViewUrls.ARTICLES_PROFILE_JSP.toString())
                    .forward(request, response);
        } else {
            Security.endSession(request, response);
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
