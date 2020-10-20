package com.ltphuc.blog.common.ui;

import com.ltphuc.blog.common.service.ResourcesHelper;

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
        ResourcesHelper resourcesHelper = new ResourcesHelper("message");
        //*************************
        E_INPUT_INVALIDATE= resourcesHelper.getValue("error.input.validate");  //;"Lỗi dữ liệu đầu vào không phù hợp";
        E_USER_GETLIST = resourcesHelper.getValue("error.user.get"); //"Lỗi khi lấy danh sách User từ cơ sở dữ liệu";
        E_USER_FINDUP = resourcesHelper.getValue("error.user.find");//"Lỗi khi đăng nhập và lấy thông tin User từ cơ sở dữ liệu";

        E_CATE_GETLIST =resourcesHelper.getValue("error.cate.get");//"Lỗi khi lấy danh sách User từ cơ sở dữ liệu";
        E_CATE_FINDUP =resourcesHelper.getValue("error.cate.find");//"Lỗi khi truy vấn thông tin Category";

        E_ADD=resourcesHelper.getValue("error.add");//"";
        E_UPDATE=resourcesHelper.getValue("error.update");//"";
        E_DELETE=resourcesHelper.getValue("error.delete");//"";

        E_BLOG_GETLIST =resourcesHelper.getValue("error.blog.get");//"Lỗi khi lấy danh sách User từ cơ sở dữ liệu";
        E_BLOG_FINDUP =resourcesHelper.getValue("error.blog.find");//"Lỗi khi truy vấn thông tin Category";

        A_ADD_SUCCESS=resourcesHelper.getValue("action.add.ok");//"Thêm mới thành công";
        A_UPDATE_SUCCESS=resourcesHelper.getValue("action.update.ok");//"Cập nhật thành công";
        A_DELETE_SUCCESS=resourcesHelper.getValue("action.delete.ok");//"Xóa thành công";
    }
}
