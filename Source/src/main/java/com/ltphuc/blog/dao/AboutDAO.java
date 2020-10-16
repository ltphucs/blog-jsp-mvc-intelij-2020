package com.ltphuc.blog.dao;

import com.ltphuc.blog.model.About;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AboutDAO extends DAOHelper implements BaseDAO<About> {

    private final String SELECT_TOP_SQL = "SELECT * FROM `about` ORDER BY `about`.id  DESC LIMIT ?";

    @Override
    public List<About> findAll() throws SQLException {
        List<About> rt = new ArrayList<>();
        setConnection();
        String FIND_ALL_SQL = "SELECT * FROM `demo-blog`.`about`";
        PreparedStatement st = connection.prepareStatement(FIND_ALL_SQL);
        ResultSet rs = st.executeQuery();
        return getAbouts(rt, rs);
        //return null;
    }

    private List<About> getAbouts(List<About> rt, ResultSet rs) throws SQLException {
        while (rs.next()) {
            int id = rs.getInt(About.COLUMN_Id);
            String title = rs.getString(About.COLUMN_Content);
            String shortContent = rs.getString(About.COLUMN_ShortContent);
            String fullContent = rs.getString(About.COLUMN_Content);
            String imageUrl = rs.getString(About.COLUMN_ImageUrl);
            About temp = new About(id, title, shortContent, fullContent, imageUrl);
            rt.add(temp);
        }
        disConnection();
        return rt;
    }

    @Override
    public boolean save(About object) throws Exception {
        setConnection();
        String SAVE_SQL = "INSERT INTO `demo-blog`.`about` (`title`, `shortcontent`, `content`, `imageurl`) VALUES (?,?,?,?);";
        PreparedStatement st;
        st = prepareStatement(object, SAVE_SQL);
        boolean rt = st.executeUpdate() == 1;
        disConnection();
        return rt;

    }

    private PreparedStatement prepareStatement(About object, String SAVE_SQL) throws SQLException {
        PreparedStatement st = connection.prepareStatement(SAVE_SQL);
        st.executeQuery("SET NAMES 'UTF8'");
        st.executeQuery("SET CHARACTER SET 'UTF8'");
        st.setString(1, object.getTitle());
        st.setString(2, object.getShortContent());
        st.setString(3, object.getContent());
        st.setString(4, object.getImageUrl());
        return st;
    }

    @Override
    public boolean update(About object) throws Exception {
        setConnection();

        String UPDATE_SQL = "UPDATE `about` SET `title`=?,`shortcontent`=?, `content`=?,`imageurl`=?  WHERE id=?";
        PreparedStatement st;
        st= prepareStatement(object, UPDATE_SQL);

        st.setInt(5,object.getId());
        boolean rt = st.executeUpdate() == 1;
        disConnection();
        return rt;
    }

    @Override
    public boolean delete(About object) throws Exception {
        setConnection();

        String DELETE_SQL = "DELETE FROM `about` WHERE id=?";
        PreparedStatement st = connection.prepareStatement(DELETE_SQL);
        st.setInt(1,object.getId());

        boolean rt = st.executeUpdate() == 1;
        disConnection();
        return rt;
    }

    @Override
    public About findById(int id) throws Exception {
        About rt = null;
        setConnection();
        String FIND_BY_ID_SQL = "SELECT * FROM `about` WHERE `about`.id=?";
        PreparedStatement st = connection.prepareStatement(FIND_BY_ID_SQL);
        st.setInt(1,id);

        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            rt = getAbout(id, rs);
        }
        disConnection();
        return rt;
    }

    private About getAbout(int id, ResultSet rs) throws SQLException {
        About rt;
        String title = rs.getString(About.COLUMN_Title);
        String shortContent = rs.getString(About.COLUMN_ShortContent);
        String fullContent = rs.getString(About.COLUMN_Content);
        String imageUrl = rs.getString(About.COLUMN_ImageUrl);
        rt = new About(id, title, shortContent, fullContent, imageUrl);
        return rt;
    }


    public About top1() throws Exception {
        About rt = null;
        setConnection();
        PreparedStatement st = connection.prepareStatement(SELECT_TOP_SQL);
        st.setInt(1,1);

        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            int id = rs.getInt(About.COLUMN_Id);
            rt = getAbout(id, rs);
        }
        disConnection();
        return rt;
    }


    public  List<About>  topN(int n) throws Exception {
        List<About> rt = new ArrayList<>();
        setConnection();
        PreparedStatement st = connection.prepareStatement(SELECT_TOP_SQL);
        st.setInt(1,n);
        ResultSet rs = st.executeQuery();
        return getAbouts(rt, rs);
    }
}
