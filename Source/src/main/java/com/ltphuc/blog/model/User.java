package com.ltphuc.blog.model;

public class User {
    private int id;
    private String username;
    private String password;
    private int status;
    private boolean isDeleted;

    public String getFullname() {
        return username;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    private String fullname;

    public static String ColumnID;
    public static String ColumnUsername;
    public static String ColumnPassword;
    public static String ColumnStatus;
    public static String ColumnIsDeleted;
    static {
        ColumnID="id";
        ColumnPassword="password";
        ColumnUsername="username";
        ColumnIsDeleted="isDeleted";
        ColumnStatus="status";
    }

    public User() {
    }

    public User(String username, String password, int status, boolean isDeleted) {
        this.username = username;
        this.password = password;
        this.status = status;
        this.isDeleted = isDeleted;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.status = 1;
        this.isDeleted = false;
    }

    public User(int id, String username, String password) {
        this.id=id;
        this.username = username;
        this.password = password;
        this.status = 1;
        this.isDeleted = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
