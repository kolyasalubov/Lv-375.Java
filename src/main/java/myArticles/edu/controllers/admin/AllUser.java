package myArticles.edu.controllers.admin;

import myArticles.edu.Services.AdminService;
import myArticles.edu.container.IocContainer;
import myArticles.edu.controllers.ControllerUrls;
import myArticles.edu.controllers.Security;
import myArticles.edu.controllers.ViewUrls;
import myArticles.edu.controllers.common.PageConfiguration;
import myArticles.edu.dto.AllUsersDto;
import myArticles.edu.dto.LoginDto;
import myArticles.edu.dto.PageInfoDto;
import myArticles.edu.dto.UsersArticleDto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/alluser")
public class AllUser extends HttpServlet {
    private static final long serialVersionUID = 16L;
    private AdminService adminService;

    public AllUser(){
        adminService = IocContainer.get().getAdminService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.isRequestedSessionIdFromCookie() && request.isRequestedSessionIdValid()) {

            String visibleUser = PageConfiguration.getVisible(request, "visibleUser");

            int pageNumber = PageConfiguration.getPageNumber(request);
            System.out.println(visibleUser + " "+ pageNumber);
            PageInfoDto pageInfoDto = new PageInfoDto(pageNumber, Integer.parseInt(visibleUser));
            AllUsersDto allUsersDto = adminService.getAllUsers(pageInfoDto);
            request.setAttribute("allUsersDto", allUsersDto);
            request.setAttribute("countUsers", allUsersDto.getUsers().size());
            getServletConfig()
                    .getServletContext()
                    .getRequestDispatcher(ViewUrls.ADMIN_PAGE_JSP.toString())
                    .forward(request, response);
        }
        else {
            Security.endSession(response);
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
