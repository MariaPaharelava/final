package by.epam.finalTask.hr.dao;

import by.epam.finalTask.hr.dao.connectionpool.exception.DAOException;

import java.util.List;
import java.util.Optional;

public interface DAO<T>{
    List<T> findAll() throws DAOException;

    Optional<T> findEntityById(int id) throws DAOException;

    void delete(int id) throws DAOException;

    void save(T entity) throws DAOException;

}
