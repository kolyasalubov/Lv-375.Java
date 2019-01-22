package myArticles.edu.controllers.articles;

import myArticles.edu.Services.ArticleService;
import myArticles.edu.Services.UserService;
import myArticles.edu.container.IocContainer;
import myArticles.edu.controllers.ControllerUrls;
import myArticles.edu.controllers.ControllersConstant;
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

/**
 * This Servlet start working when user press "Update" into "Edit Article" page
 * Update info about Article, or show error if it is impossible
 */
@WebServlet("/articleupdate")
public class ArticleUpdate extends HttpServlet {
    private static final long serialVersionUID = 8L;
    private ArticleService articleService;
    private UserService userService;


    public ArticleUpdate() {
        articleService = IocContainer.get().getArticleService();
        userService = IocContainer.get().getUserService();

    }

    /**
     * In this method we get all info from request and try to update article
     * if it is impossible add error in page
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (Security.isActiveSession(request, response)) {
            ArticleDto articleDto = new ArticleDto(
                    request.getParameter(ControllersConstant.ARTICLE_NAME.toString()),
                    request.getParameter(ControllersConstant.ARTICLE_DESCRIPTION.toString()),
                    request.getParameter(ControllersConstant.ARTICLE_URL.toString()),
                    Long.parseLong(request.getParameter(ControllersConstant.ARTICLES_USER_ID.toString())));

            if (articleService.updateArticles(articleDto)) {
                getServletConfig()
                        .getServletContext()
                        .getRequestDispatcher(ControllerUrls.USER_ARTICLES_SERVLET.toString())
                        .forward(request, response);
            }
        } else {
            Security.endSession(request, response);
            getServletConfig()
                    .getServletContext()
                    .getRequestDispatcher(ViewUrls.LOGIN_JSP.toString())
                    .forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
