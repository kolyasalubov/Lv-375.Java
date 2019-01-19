package ua.cjhrxS.Controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.cjhrxS.DTO.SignInDTO;
import ua.cjhrxS.IocContainer.IocContainer;
import ua.cjhrxS.Services.UserService;

/**
 * Servlet implementation class UserLoginServlet
 */
@WebServlet({ "/", "/signin" })
public class SignInController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService;

	private boolean isFirstStart;

	public SignInController() {
		super();
		userService = IocContainer.get().getUserService();
		isFirstStart = true;

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (isFirstStart) {
			isFirstStart = false;
			req.setAttribute(ControllerUrls.LOGIN_SERVLET.toString(), req.getRequestURL().toString());
			getServletConfig().getServletContext().getRequestDispatcher(ViewUrls.SIGNIN_JSP.toString()).forward(req,
					resp);
		} else {

			getServletConfig().getServletContext().getRequestDispatcher(ViewUrls.SIGNIN_JSP.toString()).forward(req,
					resp);

		}

	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		SignInDTO signIn = new SignInDTO(req.getParameter("user_name"), 
					    			     req.getParameter("pass_word"));
		if(userService.isValid(signIn)) {
			HttpSession session = req.getSession(true);
			session.setAttribute("Token", signIn);
			session.setMaxInactiveInterval(600000);
			
			Cookie cookie = new Cookie("id_session", session.getId());
			resp.addCookie(cookie);
			
			resp.sendRedirect(req.getContextPath() + ControllerUrls.USER_COURSE_SERVLET.toString());
			
			
		} else {
			
			req.setAttribute("Error", "Bad UserName or Password");
			getServletConfig()
			.getServletContext()
			.getRequestDispatcher(ViewUrls.SIGNIN_JSP.toString());
			
		}
		
	}

}
