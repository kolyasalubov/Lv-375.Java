package com.it.academy.dao;

import com.it.academy.entity.QueryNames;
import com.it.academy.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserDao extends ADaoExist<User> {

    public UserDao(){
        super();
    }

    @Override
    protected void init() {
        for(User.UserQueries userQueries : User.UserQueries.values()){
            sqlQueries.put(userQueries.getQueryName(), userQueries.getQuery());
        }
    }

    @Override
    protected User createInstance(List<String> list) {
        return new User(
                Long.parseLong(list.get(0)),
                list.get(1), list.get(2), list.get(3), list.get(4), list.get(5), list.get(6),
                list.get(7).equals("1"), list.get(8).equals("1")
        );
    }

    @Override
    protected List<Object> getFields(User user) {
        List<Object> list = new ArrayList<>();
        list.add(user.getId());
        list.add(user.getEmail());
        list.add(user.getPassword());
        list.add(user.getFirstName());
        list.add(user.getLastName());
        list.add(user.getPosition());
        list.add(user.getPhone());
        return list;
    }


    @Override
    protected List<Object> getUpdateFields(User user) {
        List<Object> list = getFields(user);
        list.remove(0);
        list.add(user.getId());
        return list;
    }

    @Override
    protected List<Object> getExistFields(User user) {
        List<Object> list = new ArrayList<>();
        list.add(user.getEmail());
        list.add(user.getPassword());
        return list;
    }

    public boolean updateFieldById(String setFieldName, String setFieldValue, long id) {
        String sql = String.format(sqlQueries.get(QueryNames.UPDATE_FIELD_BY_ID),
                setFieldName, setFieldValue, id);
        return executeQuery(sql, QueryNames.UPDATE_FIELD_BY_ID);
    }

    public boolean updateFieldByField(String setFieldName, String setFieldValue, String whereFieldName, String whereFieldValue) {
        String sql = String.format(sqlQueries.get(QueryNames.UPDATE_FIELD_BY_FIELD),
                setFieldName, setFieldValue, whereFieldName, whereFieldValue);
        return executeQuery(sql, QueryNames.UPDATE_FIELD_BY_FIELD);
    }
}
