package myArticles.edu.controllers;

/**
 * This class contains all view's urls
 */
public enum ViewUrls {
    LOGIN_JSP("/WEB-INF/views/users/Login.jsp"),
    USER_REGISTER_JSP("/WEB-INF/views/users/Register.jsp"),
    ADMIN_PAGE_JSP("/WEB-INF/views/admins/AdminPage.jsp"),
    USER_PROFILE_JSP("/WEB-INF/views/users/UserProfile.jsp"),
    ARTICLES_PROFILE_JSP("/WEB-INF/views/articles/ArticleProfile.jsp"),
    ARTICLES_ADD_JSP("/WEB-INF/views/articles/ArticleAdd.jsp"),
    USER_ARTICLES_JSP("/WEB-INF/views/commons/UserArticles.jsp");

    private String url;

    ViewUrls(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return url;
    }
}
