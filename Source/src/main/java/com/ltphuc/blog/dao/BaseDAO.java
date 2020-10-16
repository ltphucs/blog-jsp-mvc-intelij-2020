package com.ltphuc.blog.dao;

import java.sql.SQLException;
import java.util.List;

public interface BaseDAO<T> {
    List<T> findAll() throws SQLException;
    boolean save(T object) throws Exception;
    boolean update(T object) throws Exception;
    boolean delete(T object) throws  Exception;
    T findById(int id) throws Exception;
}
