package myArticles.edu.controllers.common;

import myArticles.edu.Services.UserArticlesService;
import myArticles.edu.container.IocContainer;
import myArticles.edu.controllers.Security;
import myArticles.edu.controllers.ViewUrls;
import myArticles.edu.dto.LoginDto;
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
        if(!Security.isActiveSession(request, response)){
            System.out.println("0");
            Security.endSession(response);
            getServletConfig()
                    .getServletContext()
                    .getRequestDispatcher(ViewUrls.LOGIN_JSP.toString())
                    .forward(request, response);
        }
        else{
            UsersArticleDto usersArticleDto = userArticlesService.getUsersArticlesDto(IocContainer.get()
                    .getUserService()
                    .getUserDto(((LoginDto)(request.getSession(false).getAttribute("loginDto")))));
            request.setAttribute("usersArticleDto", usersArticleDto);
            request.setAttribute("countArticles", usersArticleDto.getArticles().size());
            Cookie visibleArticleCookie = null;
            for (Cookie currentCookie : request.getCookies()) {
                if (currentCookie.getName().equals("visible_article")) {
                    visibleArticleCookie = currentCookie;
                    break;
                }
            }
            System.out.println("1");
            String visibleArticle = "100000";
            if (visibleArticleCookie != null) {
                visibleArticle = visibleArticleCookie.getValue();
            }
            request.setAttribute("visibleArticle", visibleArticle);
            int pageNumber = 5;
            if (request.getParameter("pageNumber") != null) {
                pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
            }
            System.out.println("2");
            request.setAttribute("pageNumber",
                    String.valueOf(pageNumber));
            getServletConfig()
                    .getServletContext()
                    .getRequestDispatcher(ViewUrls.USER_ARTICLES_JSP.toString())
                    .forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       doPost(request, response);
    }
}
