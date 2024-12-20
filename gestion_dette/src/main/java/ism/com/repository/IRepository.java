package ism.com.repository;

import java.util.List;

public interface IRepository<T> {
    void add(T entity);
    void update(T entity);
    void delete(T entity);
    T findById(Long id); 
    List<T> findAll();
}
