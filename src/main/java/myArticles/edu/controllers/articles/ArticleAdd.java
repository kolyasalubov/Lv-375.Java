package myArticles.edu.controllers.articles;

import myArticles.edu.Services.ArticleService;
import myArticles.edu.Services.UserService;
import myArticles.edu.container.IocContainer;
import myArticles.edu.controllers.ControllerUrls;
import myArticles.edu.controllers.ControllersConstant;
import myArticles.edu.controllers.Security;
import myArticles.edu.controllers.ViewUrls;
import myArticles.edu.dto.ArticleDto;
import myArticles.edu.dto.LoginDto;
import myArticles.edu.dto.UserDto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servler with is responsible for adding new Article or show, what it's impossible
 */
@WebServlet("/articleadd")
public class ArticleAdd extends HttpServlet {
    private static final long serialVersionUID = 6L;
    private ArticleService articleService;
    private UserService userService;


    public ArticleAdd() {
        articleService = IocContainer.get().getArticleService();
        userService = IocContainer.get().getUserService();

    }

    /**
     * Here we get all info about new Article from request
     * Check can we add it, and add error if not
     * @param request - HttpRequest
     * @param response - HttpResponse
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (Security.isActiveSession(request, response)) {
            LoginDto loginDto = (LoginDto) request.getSession().getAttribute(ControllersConstant.LOGIN_DTO.toString());
            Long userId = userService.getIdUserByLogin(loginDto);
            ArticleDto articleDto = new ArticleDto(
                    request.getParameter(ControllersConstant.ARTICLE_NAME.toString()),
                    request.getParameter(ControllersConstant.ARTICLE_DESCRIPTION.toString()),
                    request.getParameter(ControllersConstant.ARTICLE_URL.toString()),
                    userId);

            //Try to add Article
            if (articleService.addArticles(articleDto)) {
                response.sendRedirect(request.getContextPath() + ControllerUrls.USER_ARTICLES_SERVLET.toString());
            } else {
                //Add error into page
                request.setAttribute(ControllersConstant.ERROR.toString(), ControllersConstant.ADD_ARTICLE_ERROR.toString());
                getServletConfig()
                        .getServletContext()
                        .getRequestDispatcher(ViewUrls.ARTICLES_ADD_JSP.toString())
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
        if (Security.isActiveSession(request, response)) {
            getServletConfig()
                    .getServletContext()
                    .getRequestDispatcher(ViewUrls.ARTICLES_ADD_JSP.toString())
                    .forward(request, response);
        }
    }
}
