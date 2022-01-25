package com.youcode.gestionemployes.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.Optional;

public interface GenericDAO<T, PK extends Serializable> {
    void save(T t);

    Optional<T> findById(PK id);

    Collection<T> findAll();

    T update(T t);

    void delete(T t);
}