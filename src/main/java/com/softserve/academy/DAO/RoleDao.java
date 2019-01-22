package com.softserve.academy.DAO;

import java.util.ArrayList;
import java.util.List;

import com.softserve.academy.Entity.Role;
import com.softserve.academy.Entity.Role.RoleEntityQueries;

public final class RoleDao extends ADaoCRUD<Role> {
	private final static String ROLE_FIELDNAME = "name";

	public RoleDao() {
		super();
		init();
	}

protected void init() {
		for (RoleEntityQueries roleEntityQueries : RoleEntityQueries.values()) {
			sqlQueries.put(roleEntityQueries.getSqlQuery(), roleEntityQueries);
		}
	}

//Create a new role
	protected Role createInstance(List <String> roleParameters) {
		return new Role(
				Long.parseLong(roleParameters.get(0) == null ? "0" : roleParameters.get(0)),
				roleParameters.get(1) == null ? new String() : roleParameters.get(1));
	}
	
	//Use in updateByEntity query
	protected List <String> getUpdateFields(Role role) {
		List<String> result = new ArrayList<>();
		List<String> allFields  = getFields(role);
		result.add(allFields.get(1)); //  name
		result.add(allFields.get(0)); //  id
		return result;
	}

	protected List <String> getFields(Role role) {
		List <String> fields = new ArrayList<>();
		fields.add(role.getId().toString());
		fields.add(role.getName());
		return fields;
	}

	public Role getRoleEntityByName(String name) {
		return getByFieldName(ROLE_FIELDNAME, name).get(0);
	}

}