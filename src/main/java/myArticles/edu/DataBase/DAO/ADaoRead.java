package myArticles.edu.DataBase.DAO;


import myArticles.edu.db.ConnectionManager;
import myArticles.edu.entity.SqlQueries;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Use this class to read some information from Database
 * @param <TEntity> - show, information about which class we want
 */
abstract class ADaoRead<TEntity> implements IDaoRead<TEntity> {

    protected final Map <Enum <?>, Enum <?>> sqlQueries;

    protected ADaoRead() {
        this.sqlQueries = new HashMap <>();
    }

    /**
     * Use this method to create Entity object using info from Database
     * @param var1 - info
     * @return Entity object
     */
    protected abstract TEntity createInstance(String[] var1);

    protected abstract void init();

    /**
     * Use this method to get info from Database using Database query
     * @param query - Database query
     * @param sqlQueries - Enum query
     * @return
     */
    private List <TEntity> getQueryResult(String query, SqlQueries sqlQueries) {
        List <TEntity> all = new ArrayList();
        Statement statement = null;
        ResultSet resultSet = null;
        if (query == null) {
            throw new RuntimeException(String.format("Query not found %s", sqlQueries.name()));
        } else {
            try {
                statement = ConnectionManager.getInstance().getConnection().createStatement();
                resultSet = statement.executeQuery(query);
                String[] queryResult = new String[resultSet.getMetaData().getColumnCount()];

                while (resultSet.next()) {
                    for (int i = 0; i < queryResult.length; ++i) {
                        queryResult[i] = resultSet.getString(i + 1);
                    }

                    all.add(this.createInstance(queryResult));
                }
            } catch (SQLException var18) {
                throw new RuntimeException("Database Reading Error", var18);
            } finally {
                if (resultSet != null) {
                    try {
                        resultSet.close();
                    } catch (Exception var17) {

                    }
                }

                if (statement != null) {
                    try {
                        statement.close();
                    } catch (Exception var16) {

                    }
                }

            }
            if (all.isEmpty()) {
                throw new RuntimeException(String.format("Empty ResultSet by Query %s", query));
            } else {
                return all;
            }
        }
    }

    @Override
    public TEntity getById(Long id) {
        return this.getQueryResult(String.format((this.sqlQueries.get(SqlQueries.GET_BY_ID)).toString(), id), SqlQueries.GET_BY_ID).get(0);
    }

    @Override
    public List <TEntity> getAll() {
        return this.getQueryResult((this.sqlQueries.get(SqlQueries.GET_ALL)).toString(), SqlQueries.GET_ALL);
    }

    public List <TEntity> getByField(String fieldName, String fieldText) {
        String query = String.format((this.sqlQueries.get(SqlQueries.GET_BY_FIELD)).toString(), fieldName, fieldText);
        return this.getQueryResult(query, SqlQueries.GET_BY_FIELD);
    }

    public List <TEntity> getByTwoField(String firstField, String firstText, String secondField, String secondText) {
        String query = String.format((this.sqlQueries.get(SqlQueries.GET_BY_TWO_FIELD)).toString(), firstField, firstText, secondField, secondText);
        return this.getQueryResult(query, SqlQueries.GET_BY_TWO_FIELD);
    }


}
