package org.sementsinsky.filmSearch.dao;

import java.util.List;

public interface IRepository<T, Id extends Number> {
    void persist(T entity);
    void update(T entity);
    T getById(Id id);
    void delete(T entity);
    T getByValue(String field, String value);
    List<T> getASetByValue(String field, String value);
}
