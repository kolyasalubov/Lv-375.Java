package myArticles.edu.controllers.common;

import myArticles.edu.Services.UserArticlesService;
import myArticles.edu.container.IocContainer;
import myArticles.edu.controllers.ControllerUrls;
import myArticles.edu.controllers.ControllersConstant;
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

/**
 * this Servlet get all info about user's main page and
 * set all info with user want to see into DTO object
 */
@WebServlet("/userarticle")
public class UserArticles extends HttpServlet {
    private static final long serialVersionUID = 10L;
    private UserArticlesService userArticlesService;

    public UserArticles() {
        userArticlesService = IocContainer.get().getUserArticlesService();
    }

    /**
     * Method which work with all information about page
     * @param request - HttpRequest
     * @param response - HttpResponse
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (Security.isActiveSession(request, response)) {
            //How much Articles user want to see on page
            String visibleArticle = PageConfiguration.getVisible(request, ControllersConstant.VISIBLE_ITEM_ARTICLE.toString());
            //number of page where user is know
            int pageNumber = PageConfiguration.getPageNumber(request);

            PageInfoDto pageInfoDto = new PageInfoDto(pageNumber, Integer.parseInt(visibleArticle));
            UsersArticleDto usersArticleDto = userArticlesService.getPageUsers(IocContainer.get()
                    .getUserService()
                    .getUserDto(((LoginDto) (request.getSession(false).getAttribute(ControllersConstant.LOGIN_DTO.toString())))), pageInfoDto);
            request.setAttribute(ControllersConstant.USER_ARTICLE_DTO.toString(), usersArticleDto);
            request.setAttribute(ControllersConstant.NUMBER_OF_ARTICLES.toString(), usersArticleDto.getArticles().size());
            getServletConfig()
                    .getServletContext()
                    .getRequestDispatcher(ViewUrls.USER_ARTICLES_JSP.toString())
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
