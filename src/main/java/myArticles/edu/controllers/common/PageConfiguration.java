package myArticles.edu.controllers.common;

import javax.servlet.http.HttpServletRequest;

public class PageConfiguration {
    public static String getVisible(HttpServletRequest request , String value){
        String visible;
        if(request.getParameter(value)!=null){
            visible = request.getParameter(value);
            request.getSession().setAttribute(value, visible);
        }
        else {
            if(request.getSession().getAttribute(value)!=null){
                visible = (String)request.getSession().getAttribute(value);
                request.setAttribute(value, visible);
            }
            else {
                visible = "100000";
            }
        }
        return visible;
    }

    public static int getPageNumber(HttpServletRequest request){
        int pageNumber;
        if (request.getParameter("pageNumber") != null) {
            pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
            request.getSession().setAttribute("pageNumber", pageNumber);
        }
        else{
            if(request.getSession().getAttribute("pageNumber")!=null){
                pageNumber = (int)request.getSession().getAttribute("pageNumber");
            }
            else{
                pageNumber = 1;
            }
        }
        return pageNumber;
    }
}
