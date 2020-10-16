package com.ltphuc.blog.model;

public class About {
    public static String COLUMN_Id;
    public static String COLUMN_Title;
    public static String COLUMN_ShortContent;
    public static String COLUMN_Content;
    public static String COLUMN_ImageUrl;

    static {
        COLUMN_Id="id";
        COLUMN_Title="title";
        COLUMN_ShortContent="shortContent";
        COLUMN_Content="content";
        COLUMN_ImageUrl="imageUrl";
    }


    public About() {
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    private int id;
    private String title;
    private String shortContent;
    private String content;
    private String imageUrl;

    public About(int id,String title,String shortContent,String content,String imageUrl) {
        // TODO Auto-generated constructor stub
        this.id=id;
        this.title=title;
        this.imageUrl= imageUrl;
        this.shortContent= shortContent;
        this.content=content;
    }
    public About(int id) {
        // TODO Auto-generated constructor stub
        this.id=id;
    }
}
