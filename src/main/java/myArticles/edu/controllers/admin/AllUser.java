package myArticles.edu.controllers.admin;

import myArticles.edu.Services.AdminService;
import myArticles.edu.container.IocContainer;
import myArticles.edu.controllers.ControllerUrls;
import myArticles.edu.controllers.ControllersConstant;
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

/**
 * Servlet with is responsible for Admin page
 * In this class we analyze info about page and return Dto object to Jsp
 */
@WebServlet("/alluser")
public class AllUser extends HttpServlet {
    private static final long serialVersionUID = 12L;
    private AdminService adminService;

    public AllUser() {
        adminService = IocContainer.get().getAdminService();
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
            //How much Users admin want to see on page
            String visibleUser = PageConfiguration.getVisible(request, ControllersConstant.VISIBLE_ITEM_USER.toString());
            //NumberOfPage where admin is know
            int pageNumber = PageConfiguration.getPageNumber(request);

            PageInfoDto pageInfoDto = new PageInfoDto(pageNumber, Integer.parseInt(visibleUser));
            AllUsersDto allUsersDto = adminService.getAllUsers(pageInfoDto);
            request.setAttribute(ControllersConstant.ALL_USER_DTO.toString(), allUsersDto);
            request.setAttribute(ControllersConstant.NUMBER_OF_USERS.toString(), allUsersDto.getUsers().size());
            getServletConfig()
                    .getServletContext()
                    .getRequestDispatcher(ViewUrls.ADMIN_PAGE_JSP.toString())
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
