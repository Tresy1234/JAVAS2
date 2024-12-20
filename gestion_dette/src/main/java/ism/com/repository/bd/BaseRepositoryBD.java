package ism.com.repository.bd;

import java.sql.Connection;
import java.util.List;
import ism.com.database.DatabaseConnection;
import ism.com.repository.IRepository;

public abstract class BaseRepositoryBD<T> implements IRepository<T> {
    protected Connection connection;

    public BaseRepositoryBD() {
        this.connection = DatabaseConnection.getConnection();
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
