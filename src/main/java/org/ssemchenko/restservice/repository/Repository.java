package org.ssemchenko.restservice.repository;

public interface Repository <T, K> {
    T findById(K id);

    boolean deleteById(K id);

    T findAll();

    T save(T t);
}
