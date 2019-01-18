package com.it.academy.dao;

import com.it.academy.db.ConnectionManager;
import com.it.academy.entity.QueryNames;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

abstract public class ADaoExist<TEntity> extends ADaoCRUD<TEntity> implements IDaoExist<TEntity> {

    protected ADaoExist(){
        super();
    }

    protected abstract List<Object> getExistFields(TEntity entity);

    private long getResultSetSize (String query, QueryNames queryName) {

        Statement statement = null;
        ResultSet resultSet = null;
        long size = 0;

        if (query == null) {
            throw new RuntimeException(String.format(QUERY_NOT_FOUND, queryName.name()));
        }

        try {
            statement = ConnectionManager.getInstance().getConnection().createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                size++;
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
        return size;
    }

    public boolean isExist(TEntity entity){
        String sql = String.format(sqlQueries.get(QueryNames.IS_EXIST), getExistFields(entity).toArray());
        return getResultSetSize(sql, QueryNames.IS_EXIST) != 0;
    }
}
