package myArticles.edu.DataBase.DAO;


import myArticles.edu.entity.Article;
import myArticles.edu.entity.SqlQueries;

import java.util.ArrayList;
import java.util.List;

/**
 * Class with we use to work with Articles Table in Database
 */
public final class ArticleDao extends ADaoCRUD <Article> {
    private static final String ARTICLE_NAME = "Name";
    public ArticleDao() {
        this.init();
    }

    protected void init() {
        for (Article.ArticleEntityQueries articleEntityQueries : Article.ArticleEntityQueries.values()) {
            sqlQueries.put(articleEntityQueries.getSqlQuery(), articleEntityQueries);
        }

    }

    protected Article createInstance(String[] args) {
        return new Article(Long.parseLong(args[0] == null ? "0" : args[0]), args[1] == null ? "" : args[1], args[2] == null ? "" : args[2], args[3] == null ? "" : args[3], Long.parseLong(args[4] == null ? "0" : args[4]));
    }

    protected List getFields(Article entity) {
        List <String> result = new ArrayList <>();
        result.add(entity.getID().toString());
        result.add(entity.getName());
        result.add(entity.getDescription());
        result.add(entity.getUrl());
        result.add(entity.getUserId().toString());

        return result;
    }

    protected List getUpdateFields(Article entity) {
        List <String> result = new ArrayList <>();
        result.add(entity.getName());
        result.add(entity.getDescription());
        result.add(entity.getUrl());
        result.add(entity.getID().toString());
        return result;
    }

    public Article getArticleEntityByUsername(String articlename) {
        return getByField(ARTICLE_NAME, articlename).get(0);
    }



}
