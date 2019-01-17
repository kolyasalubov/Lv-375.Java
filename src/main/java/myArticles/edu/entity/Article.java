package myArticles.edu.entity;

import java.util.Objects;

/**
 * Class , which contains all information about Articles and Method how work with it
 *
 */
public class Article implements IEntity {
    private long articleId;
    private String name;
    private String description;
    private String url;
    private long userId;

    public Article(long articleId, String name, String description, String url, long userId) {
        this.articleId = articleId;
        this.name = name;
        this.description = description;
        this.url = url;
        this.userId = userId;
    }

    public Article(String name, String description, String url, long userId) {
        this.name = name;
        this.description = description;
        this.url = url;
        this.userId = userId;
    }

    public Long getID() {
        return this.articleId;
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

    public Long getUserId() {
        return userId;
    }

    public void setArticleId(long articleId) {
        this.articleId = articleId;
    }

    public void setName(String name) {
        name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public static enum ArticleEntityQueries {
        INSERT(SqlQueries.INSERT, "INSERT INTO ARTICLES(Name, Description, Url, UserId) VALUE( '%s', '%s', '%s', %s);"),
        GET_ALL(SqlQueries.GET_ALL, "SELECT * FROM ARTICLES ;"),
        GET_BY_ID(SqlQueries.GET_BY_ID, "SELECT * FROM ARTICLES WHERE Id = %s ;"),
        GET_BY_FIELD(SqlQueries.GET_BY_FIELD, "SELECT * FROM ARTICLES WHERE %s = '%s' ;"),
        DELETE_BY_ID(SqlQueries.DELETE_BY_ID, "DELETE FROM ARTICLES WHERE Id = %s ;"),
        UPDATE_FIELD_BY_ID(SqlQueries.UPDATE_FIELD_BY_ID, "UPDATE ARTICLES SET %s = '%s' WHERE Id = %s ;"),
        UPDATE_BY_ID(SqlQueries.UPDATE_BY_ID, "UPDATE ARTICLES SET Name = '%s', Description = '%s', Url = '%s' WHERE Id = %s ;");

        private SqlQueries sqlQuery;
        private String query;

        ArticleEntityQueries(SqlQueries sqlQuery, String query) {
            this.sqlQuery = sqlQuery;
            this.query = query;
        }

        public SqlQueries getSqlQuery() {
            return this.sqlQuery;
        }

        public String toString() {
            return this.query;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(articleId, description, url, userId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Article article = (Article) o;
        return Objects.equals(description, article.description);
    }
}
