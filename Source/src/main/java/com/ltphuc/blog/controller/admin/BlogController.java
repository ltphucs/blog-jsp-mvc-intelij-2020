package com.ltphuc.blog.controller.admin;

import com.ltphuc.blog.common.model.ResponseListModel;
import com.ltphuc.blog.common.model.ResponseModel;
import com.ltphuc.blog.common.service.SecurityHelper;
import com.ltphuc.blog.common.ui.ErrorHelper;
import com.ltphuc.blog.common.ui.MessageHelper;
import com.ltphuc.blog.model.Blog;
import com.ltphuc.blog.service.BlogService;
import com.ltphuc.blog.service.CategoryService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "BlogController",urlPatterns = "/admin-blog")
public class BlogController extends HttpServlet {
    BlogService blogService= new BlogService();
    CategoryService categoryService= new CategoryService();


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");

        if (!SecurityHelper.checkAuthentication(request,response)) {
            response.sendRedirect("login");
        }else{
            String action = request.getParameter("action");
            if (action == null)
                action = "";
            switch (action) {
                case "add":
                    this.doAdd(request, response);
                    break;
                case "edit":
                    this.doEdit(request, response);
                    break;
                case "delete":
                    break;
                default:
                    // this.showListView(request, response);
                    break;

            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");

        if (!SecurityHelper.checkAuthentication(request,response)) {
            response.sendRedirect("login");
        }else{
            String action = request.getParameter("action");
            if (action == null)
                action = "";
            switch (action) {
                case "add":
                    this.showAddView(request, response);
                    break;
                case "edit":
                    this.showEditView(request, response);
                    break;
                case "delete":
                    this.doDeleteUser(request, response);
                    break;
                default:
                    this.showListView(request, response);
                    break;

            }
        }
    }

    private void showAddView(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ResponseListModel rtCategory = this.categoryService.findAll();

        if (rtCategory.isStatus()){
            request.setAttribute("cateList", rtCategory.getList());
            RequestDispatcher rd = request.getRequestDispatcher("views/admin/blog-add.jsp");
            rd.forward(request, response);
        }else{
            ErrorHelper.showAdminErrorPage(request,response,rtCategory.getException(),rtCategory.getMessage());
        }
    }

    private void showEditView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try{
            int id = Integer.parseInt(request.getParameter("id"));
            ResponseModel rtBlog = this.blogService.findById(id);
            ResponseListModel rtCategory = this.categoryService.findAll();
            if (rtBlog.isStatus()&& rtCategory.isStatus()){
                request.setAttribute("cateList", rtCategory.getList());
                request.setAttribute("blog", rtBlog.getObject());

                RequestDispatcher rd = request.getRequestDispatcher("views/admin/blog-edit.jsp");
                rd.forward(request, response);

            }else{
                if (!rtBlog.isStatus()) {
                    ErrorHelper.showAdminErrorPage(request, response, rtBlog.getException(), rtBlog.getMessage());
                }else if (!rtCategory.isStatus()) {
                    ErrorHelper.showAdminErrorPage(request, response, rtCategory.getException(), rtCategory.getMessage());
                }
            }
        }catch (Exception ex){
            ErrorHelper.showAdminErrorPage(request, response, ex, MessageHelper.E_INPUT_INVALIDATE);
        }
    }

    private void doDeleteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            int id = Integer.parseInt(request.getParameter("id"));

            ResponseModel rt =this.blogService.delete(new Blog(id));

            if (rt.isStatus()){
                request.setAttribute("message", MessageHelper.A_DELETE_SUCCESS);
                this.showListView(request, response);
            }else{
                ErrorHelper.showAdminErrorPage(request,response,rt.getException(),rt.getMessage());
            }

        }catch (Exception ex){
            ErrorHelper.showAdminErrorPage(request, response, ex, MessageHelper.E_INPUT_INVALIDATE);
        }
    }

    private void showListView(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ResponseListModel<Blog> rt = this.blogService.findAll();
        if (rt.isStatus()){
            request.setAttribute("list", rt.getList());
            RequestDispatcher rd = request.getRequestDispatcher("views/admin/blog-index.jsp");
            rd.forward(request, response);
        }else{
            ErrorHelper.showAdminErrorPage(request,response,rt.getException(),rt.getMessage());
        }

    }

    private void doAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            String title = request.getParameter("title");
            String shortContent = request.getParameter("shortContent");
            String fullContent = request.getParameter("fullContent");
            String imageUrl = request.getParameter("imageUrl");
            int idCategory = Integer.parseInt(request.getParameter("idCategory"));

            if (title.isEmpty()||shortContent.isEmpty()||fullContent.isEmpty()||imageUrl.isEmpty()) {
                ErrorHelper.showAdminErrorPage(request, response, null, MessageHelper.E_INPUT_INVALIDATE);
                return;
            }
            Blog blog = new Blog(0,title,shortContent,fullContent,null,imageUrl,0,0,idCategory);
            ResponseModel<Blog> rt=this.blogService.save(blog);
            if (rt.isStatus()){
                request.setAttribute("message",MessageHelper.A_ADD_SUCCESS);
                this.showAddView(request, response);
            }else{
                ErrorHelper.showAdminErrorPage(request,response,rt.getException(),rt.getMessage());
            }
        }catch (Exception ex){
            ErrorHelper.showAdminErrorPage(request, response, ex, MessageHelper.E_INPUT_INVALIDATE);
        }

    }

    private void doEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String title = request.getParameter("title");
            String shortContent = request.getParameter("shortContent");
            String fullContent = request.getParameter("fullContent");
            String imageUrl = request.getParameter("imageUrl");
            int idCategory = Integer.parseInt(request.getParameter("idCategory"));
            int id = Integer.parseInt(request.getParameter("id"));

            if (title.isEmpty()||shortContent.isEmpty()||fullContent.isEmpty()||imageUrl.isEmpty()) {
                ErrorHelper.showAdminErrorPage(request, response, null, MessageHelper.E_INPUT_INVALIDATE);
                return;
            }

            Blog blog = new Blog(id,title,shortContent,fullContent,null,imageUrl,0,0,idCategory);
            ResponseModel<Blog> rt=this.blogService.update(blog);
            if (rt.isStatus()){
                request.setAttribute("message",MessageHelper.A_UPDATE_SUCCESS);
                showListView(request,response);
            }else{
                ErrorHelper.showAdminErrorPage(request,response,rt.getException(),rt.getMessage());
            }
        }catch (Exception ex){
            ErrorHelper.showAdminErrorPage(request, response, ex, MessageHelper.E_INPUT_INVALIDATE);
        }
    }
}
