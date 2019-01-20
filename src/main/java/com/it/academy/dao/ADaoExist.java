package com.it.academy.dao;

import com.it.academy.db.ConnectionManager;
import com.it.academy.entity.IEntity;
import com.it.academy.entity.QueryNames;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

abstract public class ADaoExist<TEntity extends IEntity> extends ADaoCRUD<TEntity> implements IDaoExist<TEntity> {

    protected ADaoExist(){
        super();
    }

    protected abstract List<Object> getExistFields(TEntity entity);

    private List<String> getList (String query, QueryNames queryName) {

        Statement statement = null;
        ResultSet resultSet = null;
        List<String> list = new ArrayList<>();

        if (query == null) {
            throw new RuntimeException(String.format(QUERY_NOT_FOUND, queryName.name()));
        }

        try {
            statement = ConnectionManager.getInstance().getConnection().createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                list.add(resultSet.getString(1));
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
        return list;
    }

    public boolean isExist(TEntity entity){
        String sql = String.format(sqlQueries.get(QueryNames.IS_EXIST), getExistFields(entity).toArray());
        int size = getList(sql, QueryNames.IS_EXIST).size();
        return size != 0;
    }

    public boolean isExistExceptFromCurrent(TEntity entity){
        String sql = String.format(sqlQueries.get(QueryNames.IS_EXIST), getExistFields(entity).toArray());
        List<String> list = getList(sql, QueryNames.IS_EXIST);
        if (list.size() == 0) {
            return false;
        } else {
            boolean isCurrent = (list.get(0).equals(String.valueOf(entity.getId())));
            return !((list.size() == 1) && isCurrent);
        }
    }
}
