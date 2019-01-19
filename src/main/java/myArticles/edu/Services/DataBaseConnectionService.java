package myArticles.edu.Services;

import myArticles.edu.DataBase.DAO.UserDao;

public class DataBaseConnectionService {
    private UserDao userDao;
    public DataBaseConnectionService(UserDao userDao){
        this.userDao = userDao;
    }

    public void createDataTable(){
        userDao.createUsersTable();
        userDao.createArticlesTable();
    }
}
