package myArticles.edu.dto;

public class ArticleDto {
    private String name;
    private String description;
    private String url;
    private Long userId;

    public ArticleDto( String name, String description, String url, Long userId){
        this.name = name;
        this.description = description;
        this.url = url;
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
