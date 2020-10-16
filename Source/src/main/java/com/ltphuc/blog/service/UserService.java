package com.ltphuc.blog.service;

import com.ltphuc.blog.common.ui.MessageHelper;
import com.ltphuc.blog.dao.UserDAO;
import com.ltphuc.blog.model.User;
import com.ltphuc.blog.common.model.ResponseListModel;
import com.ltphuc.blog.common.model.ResponseModel;

public class UserService implements  BaseService<User>{

    private  UserDAO userDAO = new UserDAO();
    @Override
    public ResponseListModel<User> findAll() {
        ResponseListModel<User> rt =new ResponseListModel() ;
        try {
            rt.setList(userDAO.findAll());
            rt.setStatus(true);
        }catch (Exception ex){
            rt.setStatus(false);
            rt.setException(ex);
            rt.setMessage(MessageHelper.E_USER_GETLIST);
        }
        return rt;
    }

    @Override
    public ResponseModel<User> save(User object) {
        return null;
    }

    @Override
    public ResponseModel<User> update(User object) {
        return null;
    }

    @Override
    public ResponseModel<User> delete(User object) {
        return null;
    }

    @Override
    public ResponseModel<User> findById(int id) {
        return null;
    }

    public ResponseModel<User> findByUsernamePassword(String username,String password) {
        ResponseModel<User> rt = new ResponseModel<>();
        try {
            rt.setObject(userDAO.findByUsernamePassword(username, password));
            rt.setStatus(true);
        }catch (Exception exception){
            rt.setStatus(false);
            rt.setException(exception);
            rt.setMessage(MessageHelper.E_USER_FINDUP);
        }
        return  rt;
    }



}
