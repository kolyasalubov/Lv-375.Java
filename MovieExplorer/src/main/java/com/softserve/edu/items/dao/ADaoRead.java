package com.softserve.edu.items.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.softserve.edu.items.db.ConnectionManager;
import com.softserve.edu.items.entity.SqlQueries;

	public abstract class ADaoRead<TEntity> implements  IDaoRead<TEntity> {
	protected final static String QUERY_NOT_FOUND = "Query not found %s";
	protected final static String EMPTY_RESULTSET = "Empty ResultSet by Query %s";
	protected final static String DATABASE_READING_ERROR = "Database Reading Error";
	//
	protected final Map<Enum<?>, Enum<?>> sqlQueries;

	private final static String LOGIN_FIELDNAME = "email";
	//Constructor
	protected ADaoRead() {
		this.sqlQueries = new HashMap<Enum<?>, Enum<?>>();
	}

	protected abstract TEntity createInstance(List<String> args);

	protected abstract void init();

	// Read
	private List<TEntity> getQueryResult(String query, SqlQueries sqlQueries) {
		List<TEntity> all = new ArrayList<TEntity>();
		Statement statement = null;
		ResultSet resultSet = null;
		//String[] queryResult;
		List<String> queryResult;
		if (query == null) {
			throw new RuntimeException(String.format(QUERY_NOT_FOUND, sqlQueries.name()));
		}
		try {
			statement = ConnectionManager.getInstance().getConnection().createStatement();
			System.out.println("ADaoRead: Query before execute: " + query);
			resultSet = statement.executeQuery(query);
			//queryResult = new String[resultSet.getMetaData().getColumnCount()];
			while (resultSet.next()) {
				queryResult = new ArrayList<>();
				for (int i = 0; i < resultSet.getMetaData().getColumnCount(); i++) {
					queryResult.add(resultSet.getString(i + 1));
				}
				System.out.println("queryResult " + queryResult);
				all.add(createInstance(queryResult));
			}
		} catch (SQLException e) {
			throw new RuntimeException(DATABASE_READING_ERROR, e);
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (Exception ex) {
					System.out.println("Result set " + ex.getMessage() + ex.getStackTrace());
				}
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (Exception ex) {
					System.out.println("Statement " + ex.getMessage() + ex.getStackTrace());
				}
			}
		}
		return all;
	}
	
	public List<TEntity> getAllUserMoviesOrderBy(Long userId, int offset, int limit) {
		return getQueryResult(
				String.format(sqlQueries.get(SqlQueries.GET_ALL_USER_MOVIES).toString(), userId, offset, limit),
				SqlQueries.GET_ALL_USER_MOVIES
				);
	}

	public TEntity getById(Long id) {
		return getQueryResult(
				String.format(sqlQueries.get(SqlQueries.GET_BY_ID).toString(), id),
				SqlQueries.GET_BY_ID).get(0);
	}

	public List<TEntity> getByFieldName(String fieldName, String text) {
		System.out.println("fieldName: " + fieldName + " text: " + text);
		return getQueryResult(
				String.format(sqlQueries.get(SqlQueries.GET_BY_FIELD).toString(), fieldName, text),
				SqlQueries.GET_BY_FIELD);
	}

	public List<TEntity> getAll() {
		return getQueryResult(
				sqlQueries.get(SqlQueries.GET_ALL).toString(),
				SqlQueries.GET_ALL);
	}
	
	public List<TEntity> getAllById(long userId) {
		return getQueryResult(
				String.format(sqlQueries.get(SqlQueries.GET_ALL_USER_MOVIES).toString(), userId),
				SqlQueries.GET_ALL_USER_MOVIES);
	}
	
	public List<TEntity> getAllOrderBy(String fieldName, int offset, int limit) {
		return getQueryResult(
				String.format(sqlQueries.get(SqlQueries.GET_ALL_ORDER_BY).toString(), fieldName, offset, limit),
				SqlQueries.GET_ALL_ORDER_BY);
	}
	
	public List<TEntity> getByTitleAndNotId(String title, String id) {
		return getQueryResult(
				String.format(sqlQueries.get(SqlQueries.GET_BY_TITLE_AND_NOT_ID).toString(), title, id),
				SqlQueries.GET_BY_TITLE_AND_NOT_ID);
	}
	
	public List<TEntity> search (String title, int offset, int limit) {
		return getQueryResult(
				String.format(sqlQueries.get(SqlQueries.SEARCH).toString(), title, offset, limit),
				SqlQueries.SEARCH);
	}
	
	public TEntity getUserEntityByLogin(String email) {
		return getByFieldName(LOGIN_FIELDNAME, email).get(0);
	}
	
	//TO CHECK IF RECORD EXISTS
	public List<TEntity> getByTwoId(Long id1, Long id2) {
		return getQueryResult(
				String.format(sqlQueries.get(SqlQueries.GET_BY_TWO_ID).toString(), id1, id2), 
				SqlQueries.GET_BY_TWO_ID);
	}


}
