package com.ltphuc.blog.common.ui;

public class MessageHelper {

    public static final String E_INPUT_INVALIDATE;


    public static final String E_USER_GETLIST;
    public static final String E_USER_FINDUP;
    public static final String E_CATE_GETLIST;
    public static final String E_CATE_FINDUP;
    public static final String E_ADD;
    public static final String E_UPDATE;
    public static final String E_DELETE;
    public static final String E_BLOG_GETLIST;
    public static final String E_BLOG_FINDUP;

    public static final String A_ADD_SUCCESS;
    public static final String A_UPDATE_SUCCESS;
    public static final String A_DELETE_SUCCESS;


    static {
        //read from config here
        //*************************
        E_INPUT_INVALIDATE="Lỗi dữ liệu đầu vào không phù hợp";
        E_USER_GETLIST ="Lỗi khi lấy danh sách User từ cơ sở dữ liệu";
        E_USER_FINDUP ="Lỗi khi đăng nhập và lấy thông tin User từ cơ sở dữ liệu";

        E_CATE_GETLIST ="Lỗi khi lấy danh sách User từ cơ sở dữ liệu";
        E_CATE_FINDUP ="Lỗi khi truy vấn thông tin Category";

        E_ADD="";
        E_UPDATE="";
        E_DELETE="";

        E_BLOG_GETLIST ="Lỗi khi lấy danh sách User từ cơ sở dữ liệu";
        E_BLOG_FINDUP ="Lỗi khi truy vấn thông tin Category";

        A_ADD_SUCCESS="Thêm mới thành công";
        A_UPDATE_SUCCESS="Cập nhật thành công";
        A_DELETE_SUCCESS="Xóa thành công";

    }
}
