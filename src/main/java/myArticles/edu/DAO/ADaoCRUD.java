package myArticles.edu.DAO;


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

    /**
     * Insert entity into database
     * @param entity - entity object
     * @return
     */
    @Override
    public boolean insert(TEntity entity) {
        String query = String.format((this.sqlQueries.get(SqlQueries.INSERT)).toString(), getFields(entity).subList(1, getFields(entity).size()).toArray());
        return executeQuery(query, SqlQueries.INSERT);
    }

    /**
     * delete entity by id
     * @param id entity's id
     * @return
     */
    @Override
    public boolean deleteById(Long id) {
        String query = String.format((this.sqlQueries.get(SqlQueries.DELETE_BY_ID)).toString(), id);
        return executeQuery(query, SqlQueries.DELETE_BY_ID);
    }

    /**
     * delete entity by their object
     * @param entity - entity object
     * @return
     */
    @Override
    public boolean delete(TEntity entity) {
        return deleteById(entity.getID());
    }

    /**
     * Update entity's field value by id
     * @param field - field's name
     * @param value - field's value
     * @param id - entity's id
     * @return
     */
    @Override
    public boolean updateFieldById(String field, String value, Long id) {
        String query = String.format((this.sqlQueries.get(SqlQueries.UPDATE_FIELD_BY_ID)).toString(), field, value, id);
        return executeQuery(query, SqlQueries.UPDATE_FIELD_BY_ID);
    }

    /**
     * update all info used entity object
     * @param entity - entity object
     * @return
     */
    @Override
    public boolean updateAllByEntity(TEntity entity){
        String query = String.format(sqlQueries.get(SqlQueries.UPDATE_BY_ID).toString(),
                getUpdateFields(entity).toArray());
        return executeQuery(query, SqlQueries.UPDATE_BY_ID);
    }

    /**
     * create users table
     * @return
     */
    public boolean createUsersTable(){
        String query = sqlQueries.get(SqlQueries.CREATE_USERS_TABLE).toString();
      return   executeQuery(query, SqlQueries.CREATE_USERS_TABLE);
    }
    /**
     * create articles table
     * @return
     */
    public boolean createArticlesTable(){
        String query = sqlQueries.get(SqlQueries.CREATE_ARTICLES_TABLE).toString();
        return executeQuery(query, SqlQueries.CREATE_ARTICLES_TABLE);
    }

}
