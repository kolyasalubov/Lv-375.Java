package myArticles.edu.controllers.common;

import myArticles.edu.controllers.ControllersConstant;

import javax.servlet.http.HttpServletRequest;

/**
 * This Class contains method, which allows us to get info about page with
 * user want to see
 */
public class PageConfiguration {
    /**
     * if our value is in request -> get it
     * if out value isn't here but is in session -> get it
     * else set default value
     * @param request - HttpRequest
     * @param value - our parameter which we want to get
     * @return Number of visible items on page
     */
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
    /**
     * if page's number is in request -> get it
     * if page's number isn't here but is in session -> get it
     * else set default value
     * @param request - HttpRequest
     * @return Number of page items on page
     */
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
