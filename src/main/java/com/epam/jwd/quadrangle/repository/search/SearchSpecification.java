package com.epam.jwd.quadrangle.repository.search;

public interface SearchSpecification<T> {
    boolean exists(T object);
}
