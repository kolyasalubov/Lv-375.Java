package myArticles.edu.container;


import myArticles.edu.DAO.ArticleDao;
import myArticles.edu.DAO.UserDao;
import myArticles.edu.Services.*;

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
    private DataBaseConnectionService dataBaseConnectionService;

    private IocContainer() {
        initDaos();
        initServices();
    }

    private void initDaos() {
        userDao = new UserDao();
        articleDao = new ArticleDao();
    }

    private void initServices() {
        dataBaseConnectionService = new DataBaseConnectionService(userDao);
        userService = new UserService(userDao);
        articleService = new ArticleService(articleDao);
        userArticlesService = new UserArticlesService(userDao, articleDao);
        adminService = new AdminService(userDao);
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

    public DataBaseConnectionService getDataBaseConnectionService() {
        return dataBaseConnectionService;
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