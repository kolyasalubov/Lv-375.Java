package myArticles.edu.controllers.admin;

import myArticles.edu.Services.AdminService;
import myArticles.edu.container.IocContainer;
import myArticles.edu.controllers.ControllerUrls;
import myArticles.edu.controllers.Security;
import myArticles.edu.controllers.ViewUrls;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/alluser")
public class AllUser extends HttpServlet {
    private AdminService adminService;

    public AllUser(){
        adminService = IocContainer.get().getAdminService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(!(request.isRequestedSessionIdValid() && request.isRequestedSessionIdFromCookie())){
            Security.endSession(response);
            getServletConfig()
                    .getServletContext()
                    .getRequestDispatcher(ControllerUrls.LOGIN_SERVLET.toString())
                    .forward(request, response);
        }
        else {

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
