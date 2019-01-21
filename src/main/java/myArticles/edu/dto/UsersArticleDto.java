package myArticles.edu.dto;

import java.util.ArrayList;
import java.util.List;

public class UsersArticleDto {
    private String userName;
    private List <ArticleDto> articles;


    public UsersArticleDto(String userName) {
        this.userName = userName;
        this.articles = new ArrayList <>();

    }

    public UsersArticleDto(String userName, List <ArticleDto> articles) {
        this.userName = userName;
        this.articles = articles;

    }



    public String getUserName() {
        return userName;
    }

    public List <ArticleDto> getArticles() {
        return articles;
    }

    public void addArticleDto(ArticleDto articleDto){
        articles.add(articleDto);
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setArticles(List <ArticleDto> articles) {
        this.articles = articles;
    }


}
