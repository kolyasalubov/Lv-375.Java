package com.it.academy.controllers;

import com.it.academy.common.ObjContainer;
import com.it.academy.constants.ErrorConstants;
import com.it.academy.constants.PaginationConstants;
import com.it.academy.constants.RoomConstants;
import com.it.academy.constants.UserConstants;
import com.it.academy.dto.CollectionDto;
import com.it.academy.dto.LoginDto;
import com.it.academy.service.PaginationService;
import com.it.academy.service.UserService;

import javax.servlet.http.HttpServletRequest;


/**
 * Class RequestValidator validate request for some parameter
 */
public class RequestValidator {

    UserService userService;

    public RequestValidator(UserService userService){
        this.userService = userService;
    }

    /**
     * Checks if current user is logged in
     */
    public boolean isValid(HttpServletRequest request) {
        LoginDto loginDto = (LoginDto) request.getSession().getAttribute(UserConstants.LOGIN_DTO.toString());

        return request.isRequestedSessionIdFromCookie()
                && request.isRequestedSessionIdValid()
                && loginDto != null
                && !userService.isBlocked(loginDto);
    }

    /**
     * Checks if current user is admin
     */
    public boolean isAdmin(HttpServletRequest request) {
        LoginDto loginDto = (LoginDto) request.getSession().getAttribute(UserConstants.LOGIN_DTO.toString());

        return userService.isAdmin(loginDto);
    }
}
