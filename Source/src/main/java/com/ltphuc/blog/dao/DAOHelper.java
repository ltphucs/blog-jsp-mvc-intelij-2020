package com.ltphuc.blog.dao;

import com.ltphuc.blog.common.service.ResourcesHelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAOHelper {
    private static final String jdbcUrl;
    private static final String jdbcUsername;
    private static final String jdbcPassword;

    static {
        ResourcesHelper resourcesHelper = new ResourcesHelper("app");
        jdbcUrl= resourcesHelper.getValue("db.jdbc.url");//"jdbc:mysql://localhost:3306/demo-blog?useEncoding=true&characterEncoding=UTF-8";
        jdbcPassword=resourcesHelper.getValue("db.jdbc.password");//"";
        jdbcUsername=resourcesHelper.getValue("db.jdbc.username");//"root";
    }

    protected Connection connection;

    protected void setConnection(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcUrl,jdbcUsername,jdbcPassword);
        }catch (SQLException|ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    protected void disConnection() throws SQLException {
        if (connection!=null && !connection.isClosed()){
            connection.close();
        }
    }

}
