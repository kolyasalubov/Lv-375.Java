package com.it.academy.controllers;

import com.it.academy.common.ObjContainer;
import com.it.academy.constants.UserConstants;
import com.it.academy.dto.LoginDto;
import com.it.academy.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class RequestValidator {

    public static boolean isValid(HttpServletRequest request) {
        UserService userService = ObjContainer.getInstance().getUserService();
        LoginDto loginDto = (LoginDto) request.getSession().getAttribute(UserConstants.LOGIN_DTO.toString());

        return request.isRequestedSessionIdFromCookie()
                && request.isRequestedSessionIdValid()
                && loginDto != null
                && !userService.isBlocked(loginDto);
    }
}
