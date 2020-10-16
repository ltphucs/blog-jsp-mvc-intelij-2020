package com.ltphuc.blog.service;

import com.ltphuc.blog.common.model.ResponseListModel;
import com.ltphuc.blog.common.model.ResponseModel;
import com.ltphuc.blog.common.ui.MessageHelper;
import com.ltphuc.blog.dao.CategoryDAO;
import com.ltphuc.blog.model.Category;


public class CategoryService implements BaseService<Category> {
    CategoryDAO categoryDAO= new CategoryDAO();
    @Override
    public ResponseListModel<Category> findAll() {
        ResponseListModel<Category> rt =new ResponseListModel() ;
        try {
            rt.setList(categoryDAO.findAll());
            rt.setStatus(true);
        }catch (Exception ex){
            rt.setStatus(false);
            rt.setException(ex);
            rt.setMessage(MessageHelper.E_CATE_GETLIST);
        }
        return rt;
    }

    @Override
    public ResponseModel<Category> save(Category object) {
        ResponseModel<Category> rt = new ResponseModel<>();
        try {
            rt.setStatus(categoryDAO.save(object));
        }catch (Exception exception){
            rt.setStatus(false);
            rt.setException(exception);
            rt.setMessage(MessageHelper.E_ADD);
        }
        return  rt;
    }

    @Override
    public ResponseModel<Category> update(Category object) {
        ResponseModel<Category> rt = new ResponseModel<>();
        try {
            rt.setStatus(categoryDAO.update(object));
        }catch (Exception exception){
            rt.setStatus(false);
            rt.setException(exception);
            rt.setMessage(MessageHelper.E_UPDATE);
        }
        return  rt;
    }

    @Override
    public ResponseModel<Category> delete(Category object) {
        ResponseModel<Category> rt = new ResponseModel<>();
        try {
            rt.setStatus(categoryDAO.delete(object));
        }catch (Exception exception){
            rt.setStatus(false);
            rt.setException(exception);
            rt.setMessage(MessageHelper.E_DELETE);
        }
        return  rt;
    }

    @Override
    public ResponseModel<Category> findById(int id) {
        ResponseModel<Category> rt = new ResponseModel<>();
        try {
            rt.setObject(categoryDAO.findById(id));
            rt.setStatus(true);
        }catch (Exception exception){
            rt.setStatus(false);
            rt.setException(exception);
            rt.setMessage(MessageHelper.E_CATE_FINDUP);
        }
        return  rt;
    }
}
