package com.ltphuc.blog.service;

import com.ltphuc.blog.common.model.ResponseListModel;
import com.ltphuc.blog.common.model.ResponseModel;

public interface BaseService<T> {
    ResponseListModel<T>  findAll();
    ResponseModel<T> save(T object);
    ResponseModel<T> update(T object);
    ResponseModel<T> delete(T object);
    ResponseModel<T> findById(int id);
}
