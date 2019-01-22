package com.softserve.academy.DAO;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

import com.softserve.academy.Database.ConnectionManager;
import com.softserve.academy.Entity.IEntity;
import com.softserve.academy.Entity.SqlQueries;

abstract class ADaoCRUD<TEntity extends IEntity> extends ADaoRead<TEntity> implements IDaoCRUD<TEntity> {
	protected final static String DATABASE_INPUT_ERROR = "Database Input Error";

	protected ADaoCRUD() {
		super();
	}

	protected abstract List <String> getFields(TEntity entity);
	
	protected abstract List <String> getUpdateFields(TEntity entity);

	//Use for all query execution
	private boolean executeQuery(String query, SqlQueries sqlQueries) {
		boolean result = false;
		Statement statement = null;
		if (query == null) {
			throw new RuntimeException(String.format(QUERY_NOT_FOUND, sqlQueries.name()));
		}
		try {
			statement = ConnectionManager.getInstance().getConnection().createStatement();
			result = statement.execute(query);
		} catch (SQLException e) {
			throw new RuntimeException(DATABASE_INPUT_ERROR, e);
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (Exception ex) {
					
				}
			}
		}
		return result;
	}
	
	// CreateTable in DB
	public boolean createTable() {
		String query = sqlQueries.get(SqlQueries.CREATE).toString();
	return executeQuery(query, SqlQueries.CREATE);
	}
	
	//Insert
	public boolean insert(TEntity entity) {
		List<String> allFields = getFields(entity);
		String query = String.format(sqlQueries.get(SqlQueries.INSERT).toString(),
				(Object []) (allFields.subList(1, allFields.size()).toArray()));
		return executeQuery(query, SqlQueries.INSERT);
	}

	// Update
	public boolean updateByEntity(TEntity entity) {
		String query = String.format(sqlQueries.get(SqlQueries.UPDATE_BY_ID).toString(),
				(Object[]) getUpdateFields(entity).toArray());
		return executeQuery(query, SqlQueries.UPDATE_BY_FIELD);
	}

	public boolean updateByFieldName(String fieldName, String text, String fieldCondition, String textCondition) {
		String query = String.format(sqlQueries.get(SqlQueries.UPDATE_BY_FIELD).toString(),
					fieldName, text, fieldCondition, textCondition);
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