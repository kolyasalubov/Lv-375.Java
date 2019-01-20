package myArticles.edu.controllers.common;

import javax.servlet.http.HttpServletRequest;

public class PageConfiguration {
    public static String getVisibleArticle(HttpServletRequest request){
        String visibleArticle;
        if(request.getParameter("visibleArticle")!=null){
            visibleArticle = request.getParameter("visibleArticle");
            request.getSession().setAttribute("visibleArticle", visibleArticle);
        }
        else {
            if(request.getSession().getAttribute("visibleArticle")!=null){
                visibleArticle = (String)request.getSession().getAttribute("visibleArticle");
                request.setAttribute("visibleArticle", visibleArticle);
            }
            else {
                visibleArticle = "100000";
            }
        }
        return visibleArticle;
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
