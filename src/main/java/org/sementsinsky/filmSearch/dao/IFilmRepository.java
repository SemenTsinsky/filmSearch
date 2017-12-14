package org.sementsinsky.filmSearch.dao;

import org.sementsinsky.filmSearch.entities.Film;

import java.util.List;
import java.util.UUID;

public interface IFilmRepository {
    void persist(Film entity);
    Film getByValue(String field, String value);
    List<Film> getASetByValue(String field, String value);
    void update(Film entity);
    List<Film> getAll();
    Film getById(UUID id);
    void delete(Film entity);
    void updateRating(Film film);
}
