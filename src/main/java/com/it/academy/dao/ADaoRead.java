package com.it.academy.dao;

import com.it.academy.db.ConnectionManager;
import com.it.academy.entity.QueryNames;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

abstract public class ADaoRead<TEntity> implements IDaoRead<TEntity> {

    protected final static String QUERY_NOT_FOUND = "Query not found %s";
    protected final static String EMPTY_RESULTSET = "Empty ResultSet by Query %s";
    protected final static String DATABASE_READING_ERROR = "Database Reading Error";

    protected final Map<Enum<?>, String> sqlQueries;

    protected ADaoRead() {
        this.sqlQueries = new HashMap<>();
        init();
    }

    protected abstract void init();

    protected abstract TEntity createInstance(List<String> list);

    // Read
    protected List<TEntity> getQueryResult(String query, QueryNames queryName) {

        List<TEntity> all = new ArrayList<>();
        List<String> queryResult = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;

        if (query == null) {
            throw new RuntimeException(String.format(QUERY_NOT_FOUND, queryName.name()));
        }

        try {
            statement = ConnectionManager.getInstance().getConnection().createStatement();
            resultSet = statement.executeQuery(query);
            int colQuantity = resultSet.getMetaData().getColumnCount();

            while (resultSet.next()) {
                for (int i = 0; i < colQuantity; i++) {
                    queryResult.add(resultSet.getString(i + 1));
                }
                all.add(createInstance(queryResult));
                queryResult.clear();
            }
        } catch (SQLException e) {
            throw new RuntimeException(DATABASE_READING_ERROR, e);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        if (all.isEmpty()) {
            throw new RuntimeException(String.format(EMPTY_RESULTSET, query));
        }
        return all;
    }

    public TEntity getById(long id) {
        String sql = String.format(sqlQueries.get(QueryNames.GET_BY_ID), id);
        return getQueryResult(sql, QueryNames.GET_BY_ID).get(0);
    }

    public List<TEntity> getByFieldName(String fieldName, String fieldValue) {
        String sql = String.format(sqlQueries.get(QueryNames.GET_BY_FIELD), fieldName, fieldValue);
        return getQueryResult(sql, QueryNames.GET_BY_FIELD);
    }

    public List<TEntity> getAll() {
        String sql = sqlQueries.get(QueryNames.GET_ALL);
        return getQueryResult(sql, QueryNames.GET_ALL);
    }

}
