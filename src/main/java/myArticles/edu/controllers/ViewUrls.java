package myArticles.edu.controllers;

public enum ViewUrls {
    LOGIN_JSP("/WEB-INF/views/users/Login.jsp"),
    USER_REGISTER_JSP("/WEB-INF/views/users/Register.jsp"),
    ADMIN_PAGE_JSP("/WEB-INF/views/users/AdminPage.jsp"),
    USER_PROFILE_JSP("/WEB-INF/views/users/UserProfile.jsp"),
    ARTICLES_PROFILE_JSP("/WEB-INF/views/articles/ArticleProfile.jsp"),
    ARTICLES_ADD_JSP("/WEB-INF/views/articles/ArticleAdd.jsp"),
    USER_ARTICLES_JSP("/WEB-INF/views/commons/UserArticles.jsp"),
    ERROR_JSP("/WEB-INF/views/commons/Error.jsp");

    private String url;

    ViewUrls(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return url;
    }
}
