package myArticles.edu.controllers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class Security {
    public HttpServletResponse endSession(HttpServletResponse response){
        Cookie cookie = new Cookie("name", "");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return response;
    }
}
