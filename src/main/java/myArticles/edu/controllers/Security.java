package myArticles.edu.controllers;

import myArticles.edu.dto.LoginDto;
import myArticles.edu.dto.UserDto;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * This class is responsible for Security
 */
public class Security {

    /**
     * In this method we delete cookie and session
     * @param request - HttpRequest
     * @param response - HttpRequest
     * @return response without users info
     */
    public static HttpServletResponse endSession(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
        Cookie cookie = new Cookie("name", "");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return response;
    }

    /**
     * Check is our Session active or not
     * @param request - HttpRequest
     * @param response - HttpRequest
     * @return
     */
    public static boolean isActiveSession(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false); // Do not Create new Session
        Cookie idSessionCookie = null;
        for (Cookie currentCookie : request.getCookies()) {
            if (currentCookie.getName().equals(ControllersConstant.SESSION_ID.toString())) {
                idSessionCookie = currentCookie;
                break;
            }
        }
        return (session != null)
                && (session.getAttribute(ControllersConstant.LOGIN_DTO.toString()) != null)
                && (((LoginDto) (session.getAttribute(ControllersConstant.LOGIN_DTO.toString()))).getUserName() != null)
                && (idSessionCookie != null)
                && (idSessionCookie.getValue().equals(session.getId()));
    }

    /**
     * Check is user's data correct or not
     * @param request - HttpRequest
     * @return
     */
    public static boolean checkCorrectData(HttpServletRequest request) {
        return request.getParameter(ControllersConstant.PASSWORD.toString()) != null
                && request.getParameter(ControllersConstant.REPEAT_PASSWORD.toString()) != null
                && (request.getParameter(ControllersConstant.PASSWORD.toString())
                .equals(request.getParameter(ControllersConstant.REPEAT_PASSWORD.toString())));

    }
}
