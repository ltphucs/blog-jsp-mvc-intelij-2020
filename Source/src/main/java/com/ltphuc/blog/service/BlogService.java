package com.ltphuc.blog.service;

import com.ltphuc.blog.common.model.ResponseListModel;
import com.ltphuc.blog.common.model.ResponseModel;
import com.ltphuc.blog.common.ui.MessageHelper;
import com.ltphuc.blog.dao.BlogDAO;
import com.ltphuc.blog.model.Blog;

public class BlogService implements BaseService<Blog> {
    BlogDAO blogDAO= new BlogDAO();
    @Override
    public ResponseListModel<Blog> findAll() {
        ResponseListModel<Blog> rt =new ResponseListModel() ;
        try {
            rt.setList(blogDAO.findAll());
            rt.setStatus(true);
        }catch (Exception ex){
            rt.setStatus(false);
            rt.setException(ex);
            rt.setMessage(MessageHelper.E_BLOG_GETLIST);
        }
        return rt;
    }

    @Override
    public ResponseModel<Blog> save(Blog object) {
        ResponseModel<Blog> rt = new ResponseModel<>();
        try {
            rt.setStatus(blogDAO.save(object));
        }catch (Exception exception){
            rt.setStatus(false);
            rt.setException(exception);
            rt.setMessage(MessageHelper.E_ADD);
        }
        return  rt;
    }

    @Override
    public ResponseModel<Blog> update(Blog object) {
        ResponseModel<Blog> rt = new ResponseModel<>();
        try {
            rt.setStatus(blogDAO.update(object));
        }catch (Exception exception){
            rt.setStatus(false);
            rt.setException(exception);
            rt.setMessage(MessageHelper.E_UPDATE);
        }
        return  rt;
    }

    @Override
    public ResponseModel<Blog> delete(Blog object) {
        ResponseModel<Blog> rt = new ResponseModel<>();
        try {
            rt.setStatus(blogDAO.delete(object));
        }catch (Exception exception){
            rt.setStatus(false);
            rt.setException(exception);
            rt.setMessage(MessageHelper.E_UPDATE);
        }
        return  rt;
    }

    @Override
    public ResponseModel<Blog> findById(int id) {
        ResponseModel<Blog> rt = new ResponseModel<>();
        try {
            Blog object = blogDAO.findById(id);
            rt.setStatus(true);
            rt.setObject(object);
        }catch (Exception exception){
            rt.setStatus(false);
            rt.setException(exception);
            rt.setMessage(MessageHelper.E_BLOG_FINDUP);
        }
        return  rt;
    }

    public ResponseListModel<Blog> findTopPublishDate(int n) {
        ResponseListModel<Blog> rt =new ResponseListModel() ;
        try {
            rt.setList(blogDAO.findTopPublishDate(n));
            rt.setStatus(true);
        }catch (Exception ex){
            rt.setStatus(false);
            rt.setException(ex);
            rt.setMessage(MessageHelper.E_BLOG_GETLIST);
        }
        return rt;
    }

    public ResponseListModel<Blog> findTopViews(int n) {
        ResponseListModel<Blog> rt =new ResponseListModel() ;
        try {
            rt.setList(blogDAO.findTopViews(n));
            rt.setStatus(true);
        }catch (Exception ex){
            rt.setStatus(false);
            rt.setException(ex);
            rt.setMessage(MessageHelper.E_BLOG_GETLIST);
        }
        return rt;
    }
    public ResponseListModel<Blog> findByCategory(int categoryId) {
        ResponseListModel<Blog> rt =new ResponseListModel() ;
        try {
            rt.setList(blogDAO.findByCategory(categoryId));
            rt.setStatus(true);
        }catch (Exception ex){
            rt.setStatus(false);
            rt.setException(ex);
            rt.setMessage(MessageHelper.E_BLOG_GETLIST);
        }
        return rt;
    }

    public ResponseListModel<Blog> findByCategory(int categoryId, int topN) {
        ResponseListModel<Blog> rt =new ResponseListModel() ;
        try {
            rt.setList(blogDAO.findByCategory(categoryId,topN));
            rt.setStatus(true);
        }catch (Exception ex){
            rt.setStatus(false);
            rt.setException(ex);
            rt.setMessage(MessageHelper.E_BLOG_GETLIST);
        }
        return rt;
    }


    public ResponseModel<Blog> incView(int id) {
        ResponseModel<Blog> rt = new ResponseModel<>();
        try {
            rt.setStatus(blogDAO.incView(id));
        }catch (Exception exception){
            rt.setStatus(false);
            rt.setException(exception);
            rt.setMessage(MessageHelper.E_UPDATE);
        }
        return  rt;
    }

    public ResponseModel<Blog> incLike(int id) {
        ResponseModel<Blog> rt = new ResponseModel<>();
        try {
            rt.setStatus(blogDAO.incLike(id));
        }catch (Exception exception){
            rt.setStatus(false);
            rt.setException(exception);
            rt.setMessage(MessageHelper.E_UPDATE);
        }
        return  rt;
    }
}
