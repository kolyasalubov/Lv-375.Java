package com.it.academy.dao;

import com.it.academy.db.ConnectionManager;
import com.it.academy.entity.QueryNames;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Class ADaoCRUD provides basic create, update, delete and create table operations
 */
abstract public class ADaoCRUD<TEntity> extends ADaoRead<TEntity> implements IDaoCRUD<TEntity> {

    protected final static String DATABASE_INPUT_ERROR = "Database Input Error";

    protected ADaoCRUD() {
        super();
    }

    /**
     * Convert Entity in the List with its fields
     */
    protected abstract List<Object> getFields(TEntity entity);

    /**
     * Convert Entity in the List with its fields which will be used in UPDATE queries
     */
    protected abstract List<Object> getUpdateFields(TEntity entity);

    /**
     * Executes query using Statement
     */
    protected boolean executeQuery(String query, QueryNames queryName) {
        boolean result;
        Statement statement = null;

        if (query == null) {
            throw new RuntimeException(String.format(QUERY_NOT_FOUND, queryName.name()));
        }

        try {
            statement = ConnectionManager.getInstance().getConnection().createStatement();
            result = statement.execute(query);
        } catch (SQLException e) {
            throw new RuntimeException(DATABASE_INPUT_ERROR, e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    /**
     * Inserts the entity in DB
     */
    public boolean insert(TEntity entity) {
        List<Object> fields = getFields(entity);
        fields.remove(0);
        String sql = (String.format(sqlQueries.get(QueryNames.INSERT), fields.toArray()));
        return executeQuery(sql, QueryNames.INSERT);
    }

    /**
     * Updates the entity in DB by Id
     */
    public boolean updateEntityById(TEntity entity) {
        String sql = String.format(sqlQueries.get(QueryNames.UPDATE_ROW_BY_ID),
                getUpdateFields(entity).toArray());
        return executeQuery(sql, QueryNames.UPDATE_ROW_BY_ID);
    }

    /**
     * Updates the entity in DB by field
     */
    public boolean updateEntityByField(TEntity entity, String whereFieldName, String whereFieldValue) {
        List<Object> list = getUpdateFields(entity);
        list.remove(list.size() - 1);
        list.add(whereFieldName);
        list.add(whereFieldValue);
        String sql = String.format(sqlQueries.get(QueryNames.UPDATE_ROW_BY_FIELD),
                list.toArray());
        return executeQuery(sql, QueryNames.UPDATE_ROW_BY_FIELD);
    }

    /**
     * Deletes the entity in DB by Id
     */
    public boolean deleteById(long id) {
        String sql = String.format(sqlQueries.get(QueryNames.DELETE_BY_ID), id);
        return executeQuery(sql, QueryNames.DELETE_BY_ID);
    }

    /**
     * Deletes the entity in DB by field
     */
    public boolean deleteByFieldName(String whereFieldName, String whereFieldValue) {
        String query = String.format(sqlQueries.get(QueryNames.DELETE_BY_FIELD),
                whereFieldName, whereFieldValue);
        return executeQuery(query, QueryNames.DELETE_BY_FIELD);
    }

    /**
     * Creates table if it not already exists
     */
    public boolean createTableIfNotExists(){
        return executeQuery(sqlQueries.get(QueryNames.CREATE_TABLE), QueryNames.CREATE_TABLE);
    }
}
