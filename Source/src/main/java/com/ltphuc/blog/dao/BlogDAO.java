package com.ltphuc.blog.dao;

import com.ltphuc.blog.model.Blog;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BlogDAO extends DAOHelper implements BaseDAO<Blog> {
    @Override
    public List<Blog> findAll() throws SQLException {
        List<Blog> rt = new ArrayList<>();
        setConnection();
        String SQL_FIND_ALL = "SELECT blogs.*, categories.name as categoryName FROM blogs INNER JOIN categories ON " +
                "blogs.`id-category` = categories.id ORDER BY blogs.`publish-date` DESC";
        PreparedStatement st = connection.prepareStatement(SQL_FIND_ALL);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            int id = rs.getInt(Blog.ColumnID);
            Blog temp = getBlog(id,rs);
            rt.add(temp);
        }
        disConnection();
        return rt;
        //return null;
    }

    private Blog getBlog(int id,ResultSet rs) throws SQLException {
        String title = rs.getString(Blog.ColumnTitle);
        String shortContent = rs.getString(Blog.ColumnShortContent);
        String fullContent = rs.getString(Blog.ColumnFullContent);
        Date publishDate = rs.getDate(Blog.ColumnPublishDate);
        String imageUrl = rs.getString(Blog.ColumnImageUrl);
        int views = rs.getInt(Blog.ColumnViews);
        int likes = rs.getInt(Blog.ColumnLikes);
        int idCategory = rs.getInt(Blog.ColumnIdCategory);
        String categoryName = rs.getString(Blog.ColumnCategoryName);
        Blog temp = new Blog(id, title, shortContent, fullContent, publishDate, imageUrl, views, likes, idCategory, categoryName);
        return temp;
    }

    @Override
    public boolean save(Blog object) throws Exception {
        setConnection();
        String SAVE_SQL = "INSERT INTO `demo-blog`.`blogs` (`title`, `short-content`, `full-content`, `image-url`, `id-category`) VALUES (?,?,?,?,?);";
        PreparedStatement st;
        st=prepareStatement(object, SAVE_SQL);
        boolean rt = st.executeUpdate() == 1;
        disConnection();
        return rt;

    }

    private PreparedStatement prepareStatement(Blog object, String SAVE_SQL) throws SQLException {
        PreparedStatement st = connection.prepareStatement(SAVE_SQL);
        st.executeQuery("SET NAMES 'UTF8'");
        st.executeQuery("SET CHARACTER SET 'UTF8'");
        st.setString(1, object.getTitle());
        st.setString(2, object.getShortContent());
        st.setString(3, object.getFullContent());
        st.setString(4, object.getImageUrl());
        st.setInt(5,object.getIdCategory());
        return st;
    }

    @Override
    public boolean update(Blog object) throws Exception {
        setConnection();
        String UPDATE_SQL = "UPDATE `blogs` SET `title`=?,`short-content`=?, `full-content`=?,`image-url`=?, `id-category`=?  WHERE id=?";
        PreparedStatement st = prepareStatement(object, UPDATE_SQL);
        st.setInt(6,object.getId());
        boolean rt = st.executeUpdate() == 1;
        disConnection();
        return rt;
    }

    @Override
    public boolean delete(Blog object) throws Exception {
        setConnection();
        String DELETE_SQL = "DELETE FROM blogs WHERE id=?";
        PreparedStatement st = connection.prepareStatement(DELETE_SQL);
        st.setInt(1,object.getId());
        boolean rt = st.executeUpdate() == 1;
        disConnection();
        return rt;
    }

    @Override
    public Blog findById(int id) throws Exception {
        Blog rt = null;
        setConnection();
        String SQL_FIND_BY_ID = "SELECT blogs.*, categories.name as categoryName FROM blogs INNER JOIN categories " +
                "ON blogs.`id-category` = categories.id WHERE blogs.id=?";
        PreparedStatement st = connection.prepareStatement(SQL_FIND_BY_ID);
        st.setInt(1,id);

        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            rt =getBlog(id,rs);
        }
        disConnection();
        return rt;
    }

    public List<Blog> findTopPublishDate(int n) throws SQLException {
        String SQL_SELECT_TOP_PUBLISH_DATE = "SELECT blogs.*, categories.name as categoryName "
                + "FROM blogs INNER JOIN categories ON blogs.`id-category` = categories.id "
                + "ORDER BY blogs.`publish-date` DESC LIMIT ?";
        return getBlogs(n,SQL_SELECT_TOP_PUBLISH_DATE);
    }

    public List<Blog> findTopViews(int n) throws SQLException {
        String SQL_SELECT_TOP_VIEW = "SELECT blogs.*, categories.name as "
                + "categoryName FROM blogs INNER JOIN categories ON "
                + "blogs.`id-category` = categories.id ORDER BY blogs.views "
                + "DESC LIMIT ?";;
        return getBlogs(n, SQL_SELECT_TOP_VIEW);
    }

    public List<Blog> findByCategory(int idCategory) throws SQLException {
        String SQL_FIND_BY_CATEGORY = "SELECT blogs.*, categories.name as categoryName FROM blogs INNER JOIN categories "
                + "ON blogs.`id-category` = categories.id WHERE categories.id=?";
        return getBlogs(idCategory, SQL_FIND_BY_CATEGORY);
    }
    public List<Blog> findByCategory(int idCategory, int topN) throws SQLException {
        String SQL_FIND_BY_CATEGORY_TOP = "SELECT blogs.*, categories.name as categoryName FROM blogs INNER JOIN categories "
                + "ON blogs.`id-category` = categories.id WHERE categories.id=? ORDER BY blogs.`publish-date` DESC LIMIT ?";
        return getBlogs(idCategory, SQL_FIND_BY_CATEGORY_TOP);
    }

    private List<Blog> getBlogs(int idCategory,int topN, String SQL_FIND_BY_CATEGORY_TOP) throws SQLException {
        List<Blog> rt = new ArrayList<>();
        setConnection();
        PreparedStatement st = connection.prepareStatement(SQL_FIND_BY_CATEGORY_TOP);
        st.setInt(1,idCategory);
        st.setInt(2,topN);

        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            Blog temp = getBlog(id,rs);
            rt.add(temp);
        }
        disConnection();
        return rt;
    }

    private List<Blog> getBlogs(int idCategory, String SQL_FIND_BY_CATEGORY) throws SQLException {
        List<Blog> rt = new ArrayList<>();
        setConnection();
        PreparedStatement st = connection.prepareStatement(SQL_FIND_BY_CATEGORY);
        st.setInt(1,idCategory);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            int id = rs.getInt(Blog.ColumnID);
            Blog temp = getBlog(id,rs);
            rt.add(temp);
        }
        disConnection();
        return rt;
    }

    public boolean incView(int id) throws Exception {
        setConnection();
        String SQL_INC_VIEW = " UPDATE blogs SET views=views+1 WHERE id=?";
        PreparedStatement st = connection.prepareStatement(SQL_INC_VIEW);
        st.setInt(1,id);
        boolean rt = st.executeUpdate() == 1;
        disConnection();
        return rt;

    }

    public boolean incLike(int id) throws Exception {
        setConnection();
        String SQL_INC_LIKE = " UPDATE blogs SET likes=likes+1 WHERE id=?";
        PreparedStatement st = connection.prepareStatement(SQL_INC_LIKE);
        st.setInt(1,id);
        boolean rt = st.executeUpdate() == 1;
        disConnection();
        return rt;

    }

}
