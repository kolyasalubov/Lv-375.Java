package myArticles.edu.Services;


import myArticles.edu.DAO.ArticleDao;
import myArticles.edu.DAO.UserDao;
import myArticles.edu.dto.ArticleDto;
import myArticles.edu.dto.PageInfoDto;
import myArticles.edu.dto.UserDto;
import myArticles.edu.dto.UsersArticleDto;
import myArticles.edu.entity.Article;
import myArticles.edu.entity.User;

import java.util.ArrayList;

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
     *
     * @param userDto - user info
     * @return all user's articles
     */
    public UsersArticleDto getUsersArticlesDto(UserDto userDto) {
        User user = new UserDao().getUserEntityByUsername(userDto.getUserName());
        UsersArticleDto usersArticleDto = new UsersArticleDto(user.getUserName());
        try {
            for (Article article : articleDao.getByField(UserId_Field, user.getID().toString())) {
                ArticleDto articleDto = new ArticleDto(article.getName(),
                        article.getDescription(),
                        article.getUrl(),
                        article.getUserId());

                usersArticleDto.addArticleDto(articleDto);
            }
        } catch (Exception e) {
            System.out.println("Empty Articles");
        }
        return usersArticleDto;
    }
    /**
     * In this method we get Articles with we need to show on page
     * used information in PageInfoDto object
     * @param pageInfoDto - info about page
     * @return - Dto object with all Articles with we must show
     */
    public UsersArticleDto getPageUsers(UserDto userDto, PageInfoDto pageInfoDto) {
        UsersArticleDto usersArticleDto = getUsersArticlesDto(userDto);
        if (pageInfoDto.getVisible() == 100000) {
            return usersArticleDto;
        }
        // start = -1 in case if articles is less then we need, in another case we found start index
        int start = usersArticleDto.getArticles().size() > pageInfoDto.getVisible() * (pageInfoDto.getPageNumber() - 1)
                ? pageInfoDto.getVisible() * (pageInfoDto.getPageNumber() - 1)
                : -1;
        // end = -1 in case if articles is less then we need, in another case we found end index
        int end = usersArticleDto.getArticles().size() > (pageInfoDto.getVisible() * (pageInfoDto.getPageNumber()))
                ? (pageInfoDto.getVisible() * (pageInfoDto.getPageNumber()))
                : -1;
        if (start != -1) {
                //get sublist with articles what we need
            if (end != -1) {
                usersArticleDto.setArticles(usersArticleDto.getArticles().subList(start, end));
            } else {
                //get all articles after start index
                usersArticleDto.setArticles(usersArticleDto.getArticles().subList(start, usersArticleDto.getArticles().size()));
            }
        } else {
            usersArticleDto.setArticles(new ArrayList <>());
        }
        return usersArticleDto;
    }

}
