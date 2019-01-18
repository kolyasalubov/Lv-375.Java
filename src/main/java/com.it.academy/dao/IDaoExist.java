package com.it.academy.dao;

public interface IDaoExist<TEntity> extends IDaoCRUD<TEntity>{

    boolean isExist(TEntity entity);
}
