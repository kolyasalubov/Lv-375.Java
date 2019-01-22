package com.softserve.edu.items.dao;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

import com.softserve.edu.items.db.ConnectionManager;
import com.softserve.edu.items.entity.BaseEntity;
import com.softserve.edu.items.entity.SqlQueries;

public abstract class ADaoCRUD <TEntity extends BaseEntity> 
										extends ADaoRead<TEntity> 
										implements IDaoCRUD<TEntity> {
	
	protected final static String DATABASE_INPUT_ERROR = "Database Input Error";

	protected ADaoCRUD() {
		super();
	}

	protected abstract List<String> getFields(TEntity entity);

	protected abstract List<String> getUpdateFields(TEntity entity);

	protected boolean executeQuery(String query, SqlQueries sqlQueries) {
		boolean result = false;
		Statement statement = null;
		if (query == null) {
			throw new RuntimeException(String.format(QUERY_NOT_FOUND, sqlQueries.name()));
		}
		try {
			statement = ConnectionManager.getInstance().getConnection().createStatement();
			// TODO CHECK!
			System.out.println("Query before execute: " + query);
			result = statement.execute(query);
		} catch (SQLException e) {
			throw new RuntimeException(DATABASE_INPUT_ERROR, e);
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (Exception ex) {
					System.out.println("Statement.close() " + ex.getMessage());
				}
			}
		}
		// TODO result must be return if delete Ok
		return result;
	}
	
	// Create
	public boolean create() {
		return executeQuery(sqlQueries.get(SqlQueries.CREATE).toString(), SqlQueries.CREATE);
	}
	
	public boolean createDatabase() {
		return executeQuery(sqlQueries.get(SqlQueries.CREATE_DATABASE).toString(), SqlQueries.CREATE_DATABASE);
	}
	
	public boolean insert(TEntity entity) {
		List<String> entityFields = getFields(entity);
		String query = String.format(sqlQueries.get(SqlQueries.INSERT).toString(),
					(Object[]) (entityFields.subList(1, entityFields.size()).toArray()));
		return executeQuery(query, SqlQueries.INSERT);
	}

	//Update
	public boolean updateByEntity(TEntity entity) {
		String query = String.format(sqlQueries.get(SqlQueries.UPDATE_BY_ID).toString(),
					(Object[])getUpdateFields(entity).toArray());
		return executeQuery(query, SqlQueries.UPDATE_BY_ID);
	}

	public boolean updateByFieldName(String fieldName, String fieldValue, String fieldCondition, String textCondition) {
		String query = String.format(sqlQueries.get(SqlQueries.UPDATE_BY_FIELD).toString(),
					fieldName, fieldValue, fieldCondition, textCondition);
		return executeQuery(query, SqlQueries.UPDATE_BY_FIELD);
	}

	// Delete
	public boolean deleteById(Long id) {
		String query = String.format(sqlQueries.get(SqlQueries.DELETE_BY_ID).toString(),
					id);
		return executeQuery(query, SqlQueries.DELETE_BY_ID);
	}

	public boolean deleteByFieldName(String fieldCondition, String textCondition) {
		String query = String.format(sqlQueries.get(SqlQueries.DELETE_BY_FIELD).toString(),
					fieldCondition, textCondition);
		return executeQuery(query, SqlQueries.DELETE_BY_FIELD);
	}

	public boolean delete(TEntity entity) {
		return deleteById(entity.getId());
	}

}
