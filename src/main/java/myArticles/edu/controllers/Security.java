package myArticles.edu.controllers;

import myArticles.edu.dto.LoginDto;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Security {
    public static HttpServletResponse endSession(HttpServletResponse response){
        Cookie cookie = new Cookie("name", "");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return response;
    }
    public static boolean isActiveSession(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false); // Do not Create new Session
        Cookie idSessionCookie = null;
        for (Cookie currentCookie : request.getCookies()) {
            if (currentCookie.getName().equals(ControllersConstant.SESSION_ID.toString())) {
                idSessionCookie = currentCookie;
                break;
            }
        }
        if(session==null){
            System.out.println("SESSION NULL");
            return false;
        }
        if(idSessionCookie == null){
            System.out.println("IDSESSIONCOOKIE NULL");
            return false;
        }
        if(!(idSessionCookie.getValue().equals(session.getId()))){
            System.out.println("TUPO XZ");
            return false;
        }
        return (session != null)
                && (session.getAttribute(ControllersConstant.LOGIN_DTO.toString()) != null)
                && (((LoginDto) (session.getAttribute(ControllersConstant.LOGIN_DTO.toString()))).getUserName() != null)
                && (idSessionCookie != null)
                && (idSessionCookie.getValue().equals(session.getId()));
    }
}
