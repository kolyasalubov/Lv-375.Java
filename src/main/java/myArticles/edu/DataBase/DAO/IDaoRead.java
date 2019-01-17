package myArticles.edu.DataBase.DAO;

import java.util.List;

public interface IDaoRead<TEntity> {
    TEntity getById(Long id);

    List <TEntity> getAll();

}
