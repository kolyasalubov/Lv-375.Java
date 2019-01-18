package com.it.academy.dao;

import java.util.List;

public interface IDaoRead<TEntity> {

    //Read
    TEntity getById(long id);

    List<TEntity> getByFieldName(String fieldName, String fieldValue);

    List<TEntity> getAll();
}
