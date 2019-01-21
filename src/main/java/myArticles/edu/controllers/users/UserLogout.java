package myArticles.edu.controllers.users;

import myArticles.edu.controllers.Security;
import myArticles.edu.controllers.ViewUrls;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/logout")
public class UserLogout extends HttpServlet {
    private static final long serialVersionUID = 5L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Security.endSession(response);
        getServletConfig()
                .getServletContext()
                .getRequestDispatcher(ViewUrls.LOGIN_JSP.toString())
                .forward(request, response);
    }
}
