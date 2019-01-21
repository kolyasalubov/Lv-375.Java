package myArticles.edu.DAO;

public interface IDaoCRUD<TEntity> {
    boolean insert(TEntity entity);

    boolean deleteById(Long id);

    boolean delete(TEntity entity);

    boolean updateFieldById(String field, String value, Long id);

    boolean updateAllByEntity(TEntity entity);
}
