package com.softserve.academy.DAO;

import java.util.List;

public interface IDaoRead<TEntity> {

	// Read
	TEntity getById(Long id);

	List<TEntity> getByFieldName(String fieldName, String text);

	List<TEntity> getAll();

	}