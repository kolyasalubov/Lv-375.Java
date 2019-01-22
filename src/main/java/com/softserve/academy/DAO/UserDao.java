package com.softserve.academy.DAO;

import java.util.ArrayList;
import java.util.List;

import com.softserve.academy.Entity.User;
import com.softserve.academy.Entity.User.UserEntityQueries;

public final class UserDao extends ADaoCRUD<User> {
	private final static String LOGIN_FIELDNAME = "Login";

	public UserDao() {
		super();
		init();
	}

	protected void init() {
		for (UserEntityQueries userEntityQueries : UserEntityQueries.values()) {
			sqlQueries.put(userEntityQueries.getSqlQuery(), userEntityQueries);
		}
	}

//Create a new user
	protected User createInstance(List<String> userParameters) {
		return new User(
				Long.parseLong(userParameters.get(0) == null ? "0" : userParameters.get(0)),
				userParameters.get(1) == null ? new String() : (String)userParameters.get(1),
				userParameters.get(2) == null ? new String() : userParameters.get(2),
				userParameters.get(3) == null ? new String() : userParameters.get(3),
			    Boolean.parseBoolean(userParameters.get(4) == null ? "false" : userParameters.get(4)),
			    Long.parseLong(userParameters.get(5) == null ? "0" : userParameters.get(5)));
	}

	//Use in updateByEntity query
	protected List<String> getUpdateFields(User user) {
		List<String> result = new ArrayList<>();
		List<String> allFields = getFields(user);
		result.add(0,allFields.get(1)); //  login
		result.add(1,allFields.get(2)); //  password
		result.add(2,allFields.get(3)); //  alias
		result.add(3,allFields.get(4)); // isBlocked
		result.add(4,allFields.get(5)); // idRole
		result.add(5,allFields.get(0)); // id
		return result;
	}
	
	
	protected List<String> getFields(User user) {
		List <String> fields = new ArrayList<>();
		fields.add(0, user.getId().toString());
		fields.add(1, user.getLogin());
		fields.add(2, user.getPassword());
		fields.add(3, user.getAlias());
		fields.add(4, user.isBlocked().toString());
		fields.add(5, user.getIdRole().toString());
		return fields;
	}

	public User getUserEntityByLogin(String login) {
		return getByFieldName(LOGIN_FIELDNAME, login).get(0);      
		}

}