package com.epam.jwd.quadrangle.repository;

import java.util.List;

public interface Repository<T> {

    void create(T object);

    T read(int id);

    boolean update(T oldObject, T newObject);

    boolean update(int oldId, T newObject);

    boolean delete(int id);

    boolean delete(T object);

    List<T> getAll();

    List<T> findBySpecification(Specification specification);

}
