package myArticles.edu.DataBase.DAO;


import myArticles.edu.db.ConnectionManager;
import myArticles.edu.entity.IEntity;
import myArticles.edu.entity.SqlQueries;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Class, were we realise all method, with contains in both Entity class
 * @param <TEntity>  - our Entity class User or Article
 */
abstract class ADaoCRUD<TEntity extends IEntity> extends ADaoRead<TEntity> implements IDaoCRUD<TEntity> {

    private static final String CREATE_USERS_TABLE = "CREATE TABLE IF NOT EXISTS USERS (id int NOT NULL PRIMARY KEY AUTO_INCREMENT, Username VARCHAR(50) NOT NULL , Password VARCHAR(50) NOT NULL, Email VARCHAR(50) NOT NULL , isAdmin BOOL, isBlock BOOL);";
    private static final String CREATE_ARTICLES_TABLE = "CREATE TABLE IF NOT EXISTS ARTICLES (id int NOT NULL PRIMARY KEY AUTO_INCREMENT, Name VARCHAR(50) NULL , Description VARCHAR(100) NULL , Url VARCHAR(100) NULL, UserId INT NOT NULL, FOREIGN KEY (UserId) REFERENCES users(id));";
    protected ADaoCRUD() {

    }

    /**
     * Use this method to get all field's values from Entity Class
     * @param entity - Object of Entity class
     * @return List of values
     */
    protected abstract List getFields(TEntity entity);

    /**
     * Use this method to get all field's values from Entity Class, which u want to change
     * @param entity - Object of Entity class
     * @return List of updates values
     */
    protected abstract List getUpdateFields(TEntity entity);

    /**
     * Use this method to execute your query in Database
     * @param query - Database query
     * @param sqlQueries - Enum object
     *
     */
    private boolean executeQuery(String query, SqlQueries sqlQueries) {

        Statement statement = null;
        if (query == null) {
            throw new RuntimeException(String.format("Query not found %s", sqlQueries.name()));
        } else {
            try {
                statement = ConnectionManager.getInstance().getConnection().createStatement();
                statement.execute(query);
            } catch (SQLException var13) {
                throw new RuntimeException("Database Input Error", var13);
            } finally {
                if (statement != null) {
                    try {
                        statement.close();
                    } catch (Exception var12) {

                    }
                }

            }

            return true;
        }
    }

    @Override
    public boolean insert(TEntity entity) {
        String query = String.format((this.sqlQueries.get(SqlQueries.INSERT)).toString(), getFields(entity).subList(1, getFields(entity).size()).toArray());
        return executeQuery(query, SqlQueries.INSERT);
    }

    @Override
    public boolean deleteById(Long id) {
        String query = String.format((this.sqlQueries.get(SqlQueries.DELETE_BY_ID)).toString(), id);
        return executeQuery(query, SqlQueries.DELETE_BY_ID);
    }

    @Override
    public boolean delete(TEntity entity) {
        return deleteById(entity.getID());
    }

    @Override
    public boolean updateFieldById(String field, String value, Long id) {
        String query = String.format((this.sqlQueries.get(SqlQueries.UPDATE_FIELD_BY_ID)).toString(), field, value, id);
        return executeQuery(query, SqlQueries.UPDATE_FIELD_BY_ID);
    }

    @Override
    public boolean updateAllByEntity(TEntity entity){
        String query = String.format(sqlQueries.get(SqlQueries.UPDATE_BY_ID).toString(),
                getUpdateFields(entity).toArray());
        return executeQuery(query, SqlQueries.UPDATE_BY_ID);
    }

    public boolean createUsersTable(){
      return   executeQuery(CREATE_USERS_TABLE, SqlQueries.CREATE_USERS_TABLE);
    }

    public boolean createArticlesTable(){
        return executeQuery(CREATE_ARTICLES_TABLE, SqlQueries.CREATE_ARTICLES_TABLE);
    }
}
