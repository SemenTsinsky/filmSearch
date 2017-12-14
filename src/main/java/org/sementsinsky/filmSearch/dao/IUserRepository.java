package org.sementsinsky.filmSearch.dao;

import org.sementsinsky.filmSearch.entities.Film;
import org.sementsinsky.filmSearch.entities.Mark;
import org.sementsinsky.filmSearch.entities.User;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface IUserRepository {
    void persist(User entity);
    void update(User entity);
    User getById(UUID id);
    void delete(User entity);
    User getByValue(String field, String value);
    List<User> getASetByValue(String field, String value);
    List<Mark> getMarks(User user);
    boolean markFilm(User user, Film film, int value);
    void holdOver(User user, Film film);
    Set<Film> getHoldOverSet(User user);
    List<User> getAll();
    void delFromHoldOver(User user, Film film);
}
