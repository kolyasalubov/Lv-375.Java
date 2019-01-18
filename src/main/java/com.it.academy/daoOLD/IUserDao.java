package com.it.academy.daoOLD;

import com.it.academy.entity.User;

import java.util.List;

public interface IUserDao extends IDao {

    void addUser(User user);
    List<User> getAll();
    User getInfo(long id);
    void editInfo(User user);
    boolean isExists(User user);
    void toAdmin(long id);
    void toUser(long id);
    void block(long id);
    void unblock(long id);

}
