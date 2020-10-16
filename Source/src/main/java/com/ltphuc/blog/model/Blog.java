package com.ltphuc.blog.model;

import java.util.Date;

public class Blog {
    public Blog() {
        // TODO Auto-generated constructor stub
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortContent() {
        return shortContent;
    }

    public void setShortContent(String shortContent) {
        this.shortContent = shortContent;
    }

    public String getFullContent() {
        return fullContent;
    }

    public void setFullContent(String fullContent) {
        this.fullContent = fullContent;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    private int id;
    private String title;
    private String shortContent;
    private String fullContent;
    private Date publishDate;
    private String imageUrl;
    private int views;
    private int likes;
    private int idCategory;

    public static String ColumnID;
    public static String ColumnTitle;
    public static String ColumnShortContent;
    public static String ColumnFullContent;
    public static String ColumnPublishDate;
    public static String ColumnImageUrl;
    public static String ColumnViews;
    public static String ColumnLikes;
    public static String ColumnIdCategory;
    public static String ColumnCategoryName;
    static {
        ColumnID="id";
        ColumnTitle="title";
        ColumnShortContent="short-content";
        ColumnFullContent="full-content";
        ColumnPublishDate="publish-date";
        ColumnImageUrl="image-url";
        ColumnViews="views";
       ColumnLikes="likes";
        ColumnIdCategory="id-category";
        ColumnCategoryName="categoryName";
    }

    private String categoryName;


    public Blog(int id,String title,String shortContent,String fullContent,Date publishDate, String imageUrl,int views,int likes,int idCategory) {
        this.id= id;
        this.title=title;
        this.fullContent= fullContent;
        this.idCategory=idCategory;
        this.imageUrl = imageUrl;
        this.likes= likes;
        this.publishDate = publishDate;
        this.shortContent = shortContent;
        this.views = views;

    }

    public Blog(int id,String title,String shortContent,String fullContent,Date publishDate, String imageUrl,int views,int likes,int idCategory, String categoryName) {
        this.id= id;
        this.title=title;
        this.fullContent= fullContent;
        this.idCategory=idCategory;
        this.imageUrl = imageUrl;
        this.likes= likes;
        this.publishDate = publishDate;
        this.shortContent = shortContent;
        this.views = views;
        this.categoryName = categoryName;

    }

    public Blog(int id) {
        this.id= id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
