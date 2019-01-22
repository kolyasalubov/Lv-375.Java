package myArticles.edu.DAO;


import myArticles.edu.entity.User;

import java.util.ArrayList;
import java.util.List;
/**
 * Class with we use to work with Users Table in Database
 */
public final class UserDao extends ADaoCRUD <User> {
    private static final String USERNAME_FIELDNAME = "Username";

    public UserDao() {
        this.init();
    }

    protected void init() {
        for (User.UserEntityQueries userEntityQueries : User.UserEntityQueries.values()) {
            sqlQueries.put(userEntityQueries.getSqlQuery(), userEntityQueries);
        }
    }

    protected User createInstance(String[] args) {
        return new User(Long.parseLong(args[0] == null ? "0" : args[0]), args[1] == null ? "" : args[1], args[2] == null ? "" : args[2], args[3] == null ? "" : args[3], args[4].equals("1"), args[5].equals("1"));
    }

    protected List getFields(User entity) {
        List<String> result = new ArrayList <>();
        result.add(entity.getID().toString());
        result.add(entity.getUserName());
        result.add(entity.getPassword());
        result.add(entity.getEmail());
        result.add(Boolean.toString(entity.isAdmin()));
        result.add(Boolean.toString(entity.isBlocked()));
        return result;
    }

    protected List getUpdateFields(User entity) {
        List<String> result = new ArrayList <>();
        result.add(entity.getPassword());
        result.add(entity.getEmail());
        result.add(Boolean.toString(entity.isAdmin()));
        result.add(Boolean.toString(entity.isBlocked()));
        result.add(entity.getID().toString());
        return result;
    }

    /**
     * get user object by name
     * @param username - name
     * @return - user object
     */
    public User getUserEntityByUsername(String username) {
        return getByField(USERNAME_FIELDNAME, username).get(0);
    }
}

