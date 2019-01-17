package myArticles.edu.Services;


import myArticles.edu.DataBase.DAO.ArticleDao;
import myArticles.edu.DataBase.DAO.UserDao;
import myArticles.edu.dto.ArticleDto;
import myArticles.edu.dto.UserDto;
import myArticles.edu.dto.UsersArticleDto;
import myArticles.edu.entity.Article;
import myArticles.edu.entity.User;

/**
 * Use this Class to create and work with MainPage
 */
public class UserArticlesService {
    private static final String UserId_Field = "UserId";
    private UserDao userDao;
    private ArticleDao articleDao;

    public UserArticlesService(UserDao userDao, ArticleDao articleDao) {
        this.userDao = userDao;
        this.articleDao = articleDao;
    }

    /**
     * Extract all Article for User from Database
     * @param userDto - user info
     * @return all user's articles
     */
    public UsersArticleDto getUsersArticlesDto(UserDto userDto){
        User user = new UserDao().getUserEntityByUsername(userDto.getUserName());
        UsersArticleDto usersArticleDto = new UsersArticleDto(user.getUserName());
        for(Article article : articleDao.getByField(UserId_Field, user.getID().toString())){
            ArticleDto articleDto = new ArticleDto(article.getName(),
                    article.getDescription(),
                    article.getUrl(),
                    article.getUserId());

            usersArticleDto.addArticleDto(articleDto);
        }
        return usersArticleDto;
    }

}
