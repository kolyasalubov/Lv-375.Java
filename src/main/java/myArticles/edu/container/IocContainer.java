package myArticles.edu.container;


import myArticles.edu.DataBase.DAO.ArticleDao;
import myArticles.edu.DataBase.DAO.UserDao;
import myArticles.edu.Services.AdminService;
import myArticles.edu.Services.ArticleService;
import myArticles.edu.Services.UserArticlesService;
import myArticles.edu.Services.UserService;
import myArticles.edu.controllers.Security;

public final class IocContainer {

    private static volatile IocContainer instance = null;
    //
    private UserDao userDao;
    private ArticleDao articleDao;
    //
    private UserService userService;
    private ArticleService articleService;
    private UserArticlesService userArticlesService;
    private AdminService adminService;

    private Security security;
    private IocContainer() {
        initDaos();
        initServices();
        initSecurity();
    }

    private void initDaos() {
        userDao = new UserDao();
        articleDao = new ArticleDao();
    }

    private void initServices() {
        userService = new UserService(userDao);
        articleService = new ArticleService(articleDao);
        userArticlesService = new UserArticlesService(userDao, articleDao);
        adminService = new AdminService(userDao);
    }
    private void initSecurity(){
        security = new Security();
    }

    public static IocContainer get() {
        if (instance == null) {
            synchronized (IocContainer.class) {
                if (instance == null) {
                    instance = new IocContainer();
                }
            }
        }
        return instance;
    }

    public Security getSecurity() {
        return security;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public ArticleDao getArticleDao() {
        return articleDao;
    }

    public UserService getUserService() {
        return userService;
    }

    public ArticleService getArticleService() {
        return articleService;
    }

    public UserArticlesService getUserArticlesService() {
        return userArticlesService;
    }

    public AdminService getAdminService() {
        return adminService;
    }
}