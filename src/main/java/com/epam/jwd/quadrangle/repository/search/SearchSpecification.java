package com.epam.jwd.quadrangle.repository;

public interface Specification<T> {
    boolean exists(T object);
}
