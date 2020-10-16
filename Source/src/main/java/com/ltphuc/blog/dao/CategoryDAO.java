package com.ltphuc.blog.dao;

import com.ltphuc.blog.model.Category;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO extends DAOHelper implements BaseDAO<Category> {

    @Override
    public List<Category> findAll() throws SQLException {
        List<Category> rt = new ArrayList<>();
        setConnection();
        String SQL_FIND_ALL = "SELECT * FROM categories";
        PreparedStatement st = connection.prepareStatement(SQL_FIND_ALL);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String description = rs.getString("description");
            Category temp = new Category(id, name, description);
            rt.add(temp);
        }
        disConnection();
        return rt;
    }

    @Override
    public boolean save(Category object) throws Exception {
        setConnection();
        String SAVE_SQL = "INSERT INTO categories(name,description) VALUES(?,?)";
        PreparedStatement st = connection.prepareStatement(SAVE_SQL);
        st.executeQuery("SET NAMES 'UTF8'");
        st.executeQuery("SET CHARACTER SET 'UTF8'");
        st.setString(1, object.getName());
        st.setString(2, object.getDescription());

        boolean rt = st.executeUpdate() == 1;
        disConnection();
        return rt;

    }

    @Override
    public boolean update(Category object) throws Exception {
        setConnection();
        String UPDATE_SQL = "UPDATE categories SET name=?,description=? WHERE id=?";
        PreparedStatement st = connection.prepareStatement(UPDATE_SQL);
        st.executeQuery("SET NAMES 'UTF8'");
        st.executeQuery("SET CHARACTER SET 'UTF8'");
        st.setString(1, object.getName());
        st.setString(2, object.getDescription());
        st.setInt(3, object.getId());
        boolean rt = st.executeUpdate() == 1;
        disConnection();
        return rt;
    }

    @Override
    public boolean delete(Category object) throws Exception {
        setConnection();
        String DELETE_SQL = "DELETE FROM categories WHERE id=?";
        PreparedStatement st = connection.prepareStatement(DELETE_SQL);
        st.setInt(1, object.getId());
        boolean rt = st.executeUpdate() == 1;
        disConnection();
        return rt;
    }

    @Override
    public Category findById(int id) throws Exception {
        Category rt = null;
        setConnection();

        String SQL_FIND_BY_ID = "SELECT * FROM categories WHERE id=?";
        PreparedStatement st = connection.prepareStatement(SQL_FIND_BY_ID);
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            String name = rs.getString("name");
            String description = rs.getString("description");
            rt = (new Category(id, name, description));
        }
        disConnection();
        return rt;
    }
}
