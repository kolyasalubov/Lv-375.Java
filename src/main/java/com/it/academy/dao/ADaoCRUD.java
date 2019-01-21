package com.it.academy.dao;

import com.it.academy.db.ConnectionManager;
import com.it.academy.entity.QueryNames;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

abstract public class ADaoCRUD<TEntity> extends ADaoRead<TEntity> implements IDaoCRUD<TEntity> {

    protected final static String DATABASE_INPUT_ERROR = "Database Input Error";

    protected ADaoCRUD() {
        super();
    }

    protected abstract List<Object> getFields(TEntity entity);

    protected abstract List<Object> getUpdateFields(TEntity entity);

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

    // Create
    public boolean insert(TEntity entity) {
        List<Object> fields = getFields(entity);
        fields.remove(0);
        String sql = (String.format(sqlQueries.get(QueryNames.INSERT), fields.toArray()));
        return executeQuery(sql, QueryNames.INSERT);
    }

    // Update
    public boolean updateEntityById(TEntity entity) {
        String sql = String.format(sqlQueries.get(QueryNames.UPDATE_ROW_BY_ID),
                getUpdateFields(entity).toArray());
        System.out.println(sql);
        return executeQuery(sql, QueryNames.UPDATE_ROW_BY_ID);
    }

    public boolean updateEntityByField(TEntity entity, String whereFieldName, String whereFieldValue) {
        List<Object> list = getUpdateFields(entity);
        list.remove(list.size() - 1);
        list.add(whereFieldName);
        list.add(whereFieldValue);
        String sql = String.format(sqlQueries.get(QueryNames.UPDATE_ROW_BY_FIELD),
                list.toArray());
        return executeQuery(sql, QueryNames.UPDATE_ROW_BY_FIELD);
    }

    // Delete
    public boolean deleteById(long id) {
        String sql = String.format(sqlQueries.get(QueryNames.DELETE_BY_ID), id);
        return executeQuery(sql, QueryNames.DELETE_BY_ID);
    }

    public boolean deleteByFieldName(String whereFieldName, String whereFieldValue) {
        String query = String.format(sqlQueries.get(QueryNames.DELETE_BY_FIELD),
                whereFieldName, whereFieldValue);
        return executeQuery(query, QueryNames.DELETE_BY_FIELD);
    }

    public boolean createTableIfNotExists(){
        return executeQuery(sqlQueries.get(QueryNames.CREATE_TABLE), QueryNames.CREATE_TABLE);
    }
}
