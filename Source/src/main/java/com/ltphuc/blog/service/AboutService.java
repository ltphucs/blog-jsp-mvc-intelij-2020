package com.ltphuc.blog.service;

import com.ltphuc.blog.common.model.ResponseListModel;
import com.ltphuc.blog.common.model.ResponseModel;
import com.ltphuc.blog.common.ui.MessageHelper;
import com.ltphuc.blog.dao.AboutDAO;
import com.ltphuc.blog.model.About;
import com.ltphuc.blog.model.Blog;

public class AboutService implements  BaseService<About> {
    AboutDAO aboutDAO = new AboutDAO();
    @Override
    public ResponseListModel<About> findAll() {
        ResponseListModel<About> rt =new ResponseListModel() ;
        try {
            rt.setList(aboutDAO.findAll());
            rt.setStatus(true);
        }catch (Exception ex){
            rt.setStatus(false);
            rt.setException(ex);
            rt.setMessage(MessageHelper.E_BLOG_GETLIST);
        }
        return rt;
    }

    @Override
    public ResponseModel<About> save(About object) {
        ResponseModel<About> rt = new ResponseModel<>();
        try {
            rt.setStatus(aboutDAO.save(object));
        }catch (Exception exception){
            rt.setStatus(false);
            rt.setException(exception);
            rt.setMessage(MessageHelper.E_ADD);
        }
        return  rt;
    }

    @Override
    public ResponseModel<About> update(About object) {
        ResponseModel<About> rt = new ResponseModel<>();
        try {
            rt.setStatus(aboutDAO.update(object));
        }catch (Exception exception){
            rt.setStatus(false);
            rt.setException(exception);
            rt.setMessage(MessageHelper.E_UPDATE);
        }
        return  rt;
    }

    @Override
    public ResponseModel<About> delete(About object) {
        ResponseModel<About> rt = new ResponseModel<>();
        try {
            rt.setStatus(aboutDAO.delete(object));
        }catch (Exception exception){
            rt.setStatus(false);
            rt.setException(exception);
            rt.setMessage(MessageHelper.E_UPDATE);
        }
        return  rt;
    }

    @Override
    public ResponseModel<About> findById(int id) {
        ResponseModel<About> rt = new ResponseModel<>();
        try {
            About object = aboutDAO.findById(id);
            rt.setStatus(true);
            rt.setObject(object);
        }catch (Exception exception){
            rt.setStatus(false);
            rt.setException(exception);
            rt.setMessage(MessageHelper.E_BLOG_FINDUP);
        }
        return  rt;
    }

    public ResponseModel<About> findTop1() {
        ResponseModel<About> rt = new ResponseModel<>();
        try {
            About object = aboutDAO.top1();
            rt.setStatus(true);
            rt.setObject(object);
        }catch (Exception exception){
            rt.setStatus(false);
            rt.setException(exception);
            rt.setMessage(MessageHelper.E_BLOG_FINDUP);
        }
        return  rt;
    }

    public ResponseListModel<About> topN(int n) {
        ResponseListModel<About> rt =new ResponseListModel() ;
        try {
            rt.setList(aboutDAO.topN(n));
            rt.setStatus(true);
        }catch (Exception ex){
            rt.setStatus(false);
            rt.setException(ex);
            rt.setMessage(MessageHelper.E_BLOG_GETLIST);
        }
        return rt;
    }
}
