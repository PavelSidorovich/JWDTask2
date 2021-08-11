package com.epam.jwd.repository;

public interface Specification<T> {
    boolean exists(T object);
}
