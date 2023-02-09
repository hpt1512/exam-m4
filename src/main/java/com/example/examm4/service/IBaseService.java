package com.example.examm4.service;

import java.util.List;

public interface IBaseService<E> {
    List<E> findAll();
    E findById(Integer id);
    void insert(E e);
    void update(E e);
    void delete(E e);
}
