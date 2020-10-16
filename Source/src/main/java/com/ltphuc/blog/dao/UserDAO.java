package com.ltphuc.blog.dao;

import com.ltphuc.blog.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends  DAOHelper implements BaseDAO<User>{
    @Override
    public List<User> findAll() throws SQLException {
        List<User> rt= new ArrayList<>();
        setConnection();
        String SQL_FIND_ALL = "SELECT * FROM users";
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            int id = resultSet.getInt(User.ColumnID);
            String username= resultSet.getString(User.ColumnUsername);
            String password= resultSet.getString(User.ColumnPassword);
            rt.add(new User(id,username,password));
        }
        disConnection();
        return rt;
    }

    @Override
    public boolean save(User object) throws Exception {
        return false;
    }

    @Override
    public boolean update(User object) throws Exception {
        return false;
    }

    @Override
    public boolean delete(User object) throws Exception {
        return false;
    }

    @Override
    public User findById(int id) throws Exception {
        User rt= null;
        setConnection();
        String SQL_FIND_BY_ID = "SELECT * FROM users WHERE id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID);
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            String username= resultSet.getString(User.ColumnUsername);
            String password= resultSet.getString(User.ColumnPassword);
            rt =new User(id,username,password);
        }
        disConnection();
        return rt;
    }

    public User findByUsernamePassword(String username,String password) throws SQLException {
        User rt= null;
        setConnection();
        String SQL_FIND = "SELECT * FROM users WHERE username=? AND password=?";
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            int id = resultSet.getInt(User.ColumnID);
            rt =new User(id,username,password);
        }
        disConnection();
        return rt;
    }
}
