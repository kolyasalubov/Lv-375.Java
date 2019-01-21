package myArticles.edu.controllers.common;

import myArticles.edu.controllers.ControllersConstant;

import javax.servlet.http.HttpServletRequest;

public class PageConfiguration {
    public static String getVisible(HttpServletRequest request, String value) {
        String visible;
        if (request.getParameter(value) != null) {
            visible = request.getParameter(value);
            request.getSession().setAttribute(value, visible);
        } else {
            if (request.getSession().getAttribute(value) != null) {
                visible = (String) request.getSession().getAttribute(value);
                request.setAttribute(value, visible);
            } else {
                visible = ControllersConstant.DEFAULT_VISIBLE_VALUE.toString();
            }
        }
        return visible;
    }

    public static int getPageNumber(HttpServletRequest request) {
        int pageNumber;
        if (request.getParameter(ControllersConstant.PAGE_NUMBER.toString()) != null) {
            pageNumber = Integer.parseInt(request.getParameter(ControllersConstant.PAGE_NUMBER.toString()));
            request.getSession().setAttribute(ControllersConstant.PAGE_NUMBER.toString(), pageNumber);
        } else {
            if (request.getSession().getAttribute(ControllersConstant.PAGE_NUMBER.toString()) != null) {
                pageNumber = (int) request.getSession().getAttribute(ControllersConstant.PAGE_NUMBER.toString());
            } else {
                pageNumber = 1;
            }
        }
        return pageNumber;
    }
}
