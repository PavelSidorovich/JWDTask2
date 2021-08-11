package com.epam.jwd.repository;

import java.util.List;

public interface Repository<T> {

    void create(T object);

    T read(int id);

    boolean update(T oldObject, T newObject);

    boolean delete(int id);

    List<T> getAll();

    List<T> findBySpecification(Specification specification);

}
