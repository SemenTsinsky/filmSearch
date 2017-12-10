package org.sementsinsky.filmSearch.dao;

import java.util.List;
import java.util.UUID;

public interface IRepository<T> {
    void persist(T entity);
    void update(T entity);
    T getById(UUID id);
    void delete(T entity);
    T getByValue(String field, String value);
    List<T> getASetByValue(String field, String value);
}
