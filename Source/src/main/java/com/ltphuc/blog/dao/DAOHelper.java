package com.ltphuc.blog.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAOHelper {
    private static final String jdbcUrl;
    private static final String jdbcUsername;
    private static final String jdbcPassword;

    static {
        jdbcUrl="jdbc:mysql://localhost:3306/demo-blog?useEncoding=true&characterEncoding=UTF-8";
        jdbcPassword="";
        jdbcUsername="root";
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
