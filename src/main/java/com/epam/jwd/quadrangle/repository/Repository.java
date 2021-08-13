package com.epam.jwd.quadrangle.repository;

import java.util.List;

public interface Repository<T> {

    boolean create(T object);

    T read(int index);

    int read(T object);

    boolean update(T oldObject, T newObject);

    boolean update(int index, T newObject);

    boolean delete(int index);

    boolean delete(T object);

    List<T> getAll();

    List<T> findBySpecification(Specification specification);

}
