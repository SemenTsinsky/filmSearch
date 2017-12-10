package org.sementsinsky.filmSearch.entities;

public interface IEntity<T extends Number> {

    T getId();
    void setId(T id);
}
