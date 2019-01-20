package myArticles.edu.Services;


import myArticles.edu.DataBase.DAO.ArticleDao;
import myArticles.edu.DataBase.DAO.UserDao;
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
     * @param userDto - user info
     * @return all user's articles
     */
    public UsersArticleDto getUsersArticlesDto(UserDto userDto){
        User user = new UserDao().getUserEntityByUsername(userDto.getUserName());
        UsersArticleDto usersArticleDto = new UsersArticleDto(user.getUserName());
        try{
        for(Article article : articleDao.getByField(UserId_Field, user.getID().toString())){
            ArticleDto articleDto = new ArticleDto(article.getName(),
                    article.getDescription(),
                    article.getUrl(),
                    article.getUserId());

            usersArticleDto.addArticleDto(articleDto);
        }
        }catch (Exception e){
            System.out.println("Empty Articles");
        }
        return usersArticleDto;
    }

    public UsersArticleDto getPageUsers(UserDto userDto, PageInfoDto pageInfoDto){
        UsersArticleDto usersArticleDto= getUsersArticlesDto(userDto);
        if(pageInfoDto.getVisibleArticles() == 100000){
            return  usersArticleDto;
        }
        int start = usersArticleDto.getArticles().size() > pageInfoDto.getVisibleArticles()* (pageInfoDto.getPageNumber()-1)
                ? pageInfoDto.getVisibleArticles()* (pageInfoDto.getPageNumber()-1)
                : -1;
        int end = usersArticleDto.getArticles().size() > (pageInfoDto.getVisibleArticles()* (pageInfoDto.getPageNumber()))
                ? (pageInfoDto.getVisibleArticles()* (pageInfoDto.getPageNumber()))
                : -1;
        if(start != -1){
            if(end != -1){
                usersArticleDto.setArticles(usersArticleDto.getArticles().subList(start, end));
            }
            else {
                usersArticleDto.setArticles(usersArticleDto.getArticles().subList(start, usersArticleDto.getArticles().size()));
            }
        }
        else {
            usersArticleDto.setArticles(new ArrayList <>());
        }
        System.out.println(start +" "+end);
        return usersArticleDto;
    }

}
