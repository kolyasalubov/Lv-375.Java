package com.softserve.edu.items.dao;


import java.util.Arrays;
import java.util.List;
import com.softserve.edu.items.entity.Users;
import com.softserve.edu.items.entity.Users.UserEntityQueries;

public class UsersDao extends ADaoCRUD<Users> {
	private final static String LOGIN_FIELDNAME = "email";

	public UsersDao() {
		super();
		init();
	}
	
	@Override
	protected void init() {
		for (UserEntityQueries userEntityQueries : UserEntityQueries.values()) {
			sqlQueries.put(userEntityQueries.getSqlQuery(), userEntityQueries);
		}
	}
	
	@Override
	protected Users createInstance(List<String> args) {
		return new Users(
			Long.parseLong(args.get(0) == null ? "0" : args.get(0)), //id
			args.get(1) == null ? new String() : args.get(1), 
			args.get(2) == null ? new String() : args.get(2),
			args.get(3) == null ? new String() : args.get(3),
			args.get(4) == null ? new String() : args.get(4),
			Integer.parseInt(args.get(5) == null ? "1" : args.get(5)) //by default user isActive
		);
	}
	
	@Override
	protected List<String> getUpdateFields(Users entity) {
		List<String> fields = Arrays.asList(
			entity.getFullName(),
			entity.getRole().toString(),
			entity.getPassword(),
			entity.getEmail(),
			String.valueOf(entity.getIsActive()),
			String.valueOf(entity.getId())
		);
		return fields; 
	}

	@Override
	protected List<String> getFields(Users entity) {
		List<String> fields = Arrays.asList(	
			entity.getId().toString(),
			entity.getFullName(),
			entity.getRole().toString(),
			entity.getPassword(),
			entity.getEmail(),
			String.valueOf(entity.getIsActive())
		);
		return fields;
	}
	

	
	public Boolean createAdmin(Users user) {
		if(getByFieldName(LOGIN_FIELDNAME, user.getEmail()).isEmpty()) {
			return insert(user);
		}
		return false;
	}

}
