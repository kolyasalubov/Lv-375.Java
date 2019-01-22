package myArticles.edu.Services;

import myArticles.edu.DAO.UserDao;

/**
 * This class is responsible for creation new database's tables
 */
public class DataBaseConnectionService {
    private UserDao userDao;

    public DataBaseConnectionService(UserDao userDao) {
        this.userDao = userDao;
    }

    public void createDataTable() {
        userDao.createUsersTable();
        userDao.createArticlesTable();
    }
}
