package com.it.academy.dao;

public interface IDaoCRUD<TEntity> extends IDaoRead<TEntity> {

    // Create
    boolean insert(TEntity entity);

    // Update
    boolean updateEntityById(TEntity entity);

    boolean updateEntityByField(TEntity entity, String whereFieldName, String whereFieldValue);

    // Delete
    boolean deleteById(long id);

    boolean deleteByFieldName(String whereFieldName, String whereFieldValue);

    boolean createTableIfNotExists();

}
