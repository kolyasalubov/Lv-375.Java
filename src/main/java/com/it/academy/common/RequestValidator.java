package com.it.academy.common;

import com.it.academy.constants.UserConstants;

import javax.servlet.http.HttpServletRequest;

public class RequestValidator {

    public static boolean isValid(HttpServletRequest request){
        return request.isRequestedSessionIdFromCookie()
                && request.isRequestedSessionIdValid()
                && request.getSession().getAttribute(UserConstants.LOGIN_DTO.toString()) != null;
    }
}
