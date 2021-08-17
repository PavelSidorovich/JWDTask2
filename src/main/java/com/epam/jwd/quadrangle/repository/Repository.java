package com.epam.jwd.quadrangle.repository;

import com.epam.jwd.quadrangle.repository.search.SearchSpecification;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Flow.Publisher;

public interface Repository<T, R> {

    Publisher<R> create(T object);

    T read(int index);

    int read(T object);

    Publisher<R> update(T oldObject, T newObject);

    Publisher<R> update(int index, T newObject);

    boolean delete(int index);

    boolean delete(T object);

    List<T> getAll();

    List<T> findBySpecification(SearchSpecification<T> specification);

    List<T> sortByComparator(Comparator<T> comparator);

}
