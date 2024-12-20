package ism.com.repository.list;

import java.util.ArrayList;
import java.util.List;

import ism.com.repository.IRepository;

public abstract class BaseRepositoryList<T> implements IRepository<T> {
    protected List<T> storage;

    public BaseRepositoryList() {
        this.storage = new ArrayList<>();
    }

    @Override
    public abstract void add(T entity);

    @Override
    public abstract void update(T entity);

    @Override
    public abstract void delete(T entity);

    @Override
    public abstract T findById(Long id);

    @Override
    public abstract List<T> findAll();
}
