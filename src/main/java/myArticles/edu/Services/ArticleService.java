package myArticles.edu.Services;


import myArticles.edu.DAO.ArticleDao;
import myArticles.edu.dto.ArticleDto;
import myArticles.edu.entity.Article;

/**
 * Use this class to connect with DAO layer and process info from controllers
 * Work only with Article
 */
public class ArticleService {
    private static final String ARTICLENAME = "Name";
    private ArticleDao articleDao;

    public ArticleService(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }

    /**
     * Get ArticleDto using only Article's id
     *
     * @param id -  Article's id
     * @return ArticleDto
     */
    public ArticleDto getArticleDto(Long id) {
        Article article = articleDao.getById(id);
        return new ArticleDto(
                article.getName(),
                article.getDescription(),
                article.getUrl(),
                article.getUserId());
    }

    /**
     * Add new article in Database
     *
     * @param articleDto - ArticleDto, object with info about article
     */
    public boolean addArticles(ArticleDto articleDto) {
        Article article = new Article(articleDto.getName(),
                articleDto.getDescription(),
                articleDto.getUrl(),
                articleDto.getUserId());

        return checkValid(articleDto) && articleDao.insert(article);
    }

    public ArticleDto getFullInfo(ArticleDto articleDto) {
        Article article = articleDao.getByField(ARTICLENAME, articleDto.getName()).get(0);
        ArticleDto newarticleDto = new ArticleDto(article.getName(), article.getDescription(), article.getUrl(), article.getUserId());
        return newarticleDto;
    }

    /**
     * Update info about Article in database
     *
     * @param articleDto - new info about Article
     */
    public boolean updateArticles(ArticleDto articleDto) {
        Long id = articleDao.getArticleEntityByUsername(articleDto.getName()).getID();
        Article article = new Article(id,
                articleDto.getName(),
                articleDto.getDescription(),
                articleDto.getUrl(),
                articleDto.getUserId());

        return articleDao.updateAllByEntity(article);
    }

    private boolean checkValid(ArticleDto articleDto) {
        boolean result = true;
        try {
            if (articleDao.getByField(ARTICLENAME, articleDto.getName()).size() > 1) ;
            result = false;
        } catch (Exception e) {
            //can update user
        }
        return result;
    }

    /**
     * Delete Article from Database
     *
     * @param articleDto - which Article we must to delete
     */
    public boolean deleteArticles(ArticleDto articleDto) {
        Long id = articleDao.getArticleEntityByUsername(articleDto.getName()).getID();
        return articleDao.deleteById(id);
    }


}
