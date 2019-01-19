package myArticles.edu.dto;

import java.util.ArrayList;
import java.util.List;

public class UsersArticleDto {
    private final int DEFAULT_PAGE_OFFSET = 10;
    private String userName;
    private List <ArticleDto> articles;
    private int pageCount;
    private int numberOfPage;

    public UsersArticleDto(String userName) {
        this.userName = userName;
        this.articles = new ArrayList <>();
        this.pageCount = articles.size() / DEFAULT_PAGE_OFFSET + 1;
        this.numberOfPage = DEFAULT_PAGE_OFFSET;
        //TODO page size
    }

    public UsersArticleDto(String userName, List <ArticleDto> articles) {
        this.userName = userName;
        this.articles = articles;
        this.pageCount = articles.size() / DEFAULT_PAGE_OFFSET + 1;
        this.numberOfPage = DEFAULT_PAGE_OFFSET;
        //TODO page size
    }

    public int getNumberOfPage() {
        return numberOfPage;
    }

    public int getPageCount() {
        return pageCount;
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

    public void setNumberOfPage(int numberOfPage) {
        this.numberOfPage = numberOfPage;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }
}
