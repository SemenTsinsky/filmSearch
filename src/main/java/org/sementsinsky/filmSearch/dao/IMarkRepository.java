package org.sementsinsky.filmSearch.dao;

import org.sementsinsky.filmSearch.entities.Mark;

import java.util.List;
import java.util.UUID;

public interface IMarkRepository {
    void persist(Mark entity);
    Mark getByValue(String field, String value);
    List<Mark> getASetByValue(String field, String value);
    void update(Mark entity);
    List<Mark> getAll();
    Mark getById(UUID id);
    void delete(Mark entity);
}
