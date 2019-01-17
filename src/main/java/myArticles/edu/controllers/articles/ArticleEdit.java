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

@WebServlet("/articleedit")
public class ArticleEdit extends HttpServlet {
    private static final long serialVersionUID = 7L;
    private ArticleService articleService;
    private UserService userService;
    private Security security;

    ArticleEdit(){
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

            request.getSession().setAttribute("articleDto",  articleDto);
            getServletConfig()
                    .getServletContext()
                    .getRequestDispatcher(ViewUrls.ARTICLES_PROFILE_JSP.toString())
                    .forward(request, response);
        }
        else{
            security.endSession(response).sendRedirect(ViewUrls.LOGIN_JSP.toString());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
