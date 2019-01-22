package com.softserve.edu.items.controllers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.softserve.edu.items.dto.LoginDto;

/**
 * Class with a single method for session checking
 * 
 * @author y3809
 *
 */
public class SessionManager {

    /*
     * Method checks if session is authorized Session is valid only if is has right
     * session id and loginDto instance
     */
    public static boolean checkSession(HttpServletRequest request) {
	boolean isActiveSession = true;
	Cookie idSessionCookie = null;
	HttpSession session = request.getSession(false);

	for (Cookie currentCookie : request.getCookies()) {
	    if (currentCookie.getName().equals("id_session")) {
		idSessionCookie = currentCookie;
		break;
	    }
	}

	isActiveSession = isActiveSession && (session != null) && (session.getAttribute("loginDto") != null)
		&& (((LoginDto) (session.getAttribute("loginDto"))).getLogin() != null) && (idSessionCookie != null)
		&& (((LoginDto) (session.getAttribute("loginDto"))).getPassword().length() > 0);
	isActiveSession = isActiveSession && (idSessionCookie.getValue().equals(session.getId()));

	return isActiveSession;
    }
}
