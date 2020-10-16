package com.ltphuc.blog.controller.admin;

import com.ltphuc.blog.common.model.ResponseListModel;
import com.ltphuc.blog.common.model.ResponseModel;
import com.ltphuc.blog.common.service.SecurityHelper;
import com.ltphuc.blog.common.ui.ErrorHelper;
import com.ltphuc.blog.common.ui.MessageHelper;
import com.ltphuc.blog.model.Blog;
import com.ltphuc.blog.model.Category;
import com.ltphuc.blog.service.BlogService;
import com.ltphuc.blog.service.CategoryService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "CategoryController", urlPatterns = "/admin-category")
public class CategoryController extends HttpServlet {

    private CategoryService catService = new CategoryService();
    private BlogService blogService = new BlogService();


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");

        HttpSession session = request.getSession();

        String username = (String) session.getAttribute("username");

        if (username == null || username.isEmpty()) {
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
        Category cate = new Category();
        request.setAttribute("cate", cate);

        RequestDispatcher rd = request.getRequestDispatcher("views/admin/category-add.jsp");
        rd.forward(request, response);

    }

    private void showEditView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        ResponseModel rt = this.catService.findById(id);
        if (rt.isStatus()){
            request.setAttribute("cate", rt.getObject());
            RequestDispatcher rd = request.getRequestDispatcher("views/admin/category-edit.jsp");
            rd.forward(request, response);
        }else{
            ErrorHelper.showAdminErrorPage(request,response,rt.getException(),rt.getMessage());
        }


    }

    private void doDeleteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        ResponseModel rt =this.catService.delete(new Category(id));

        if (rt.isStatus()){
            request.setAttribute("message",MessageHelper.A_DELETE_SUCCESS);
            this.showListView(request, response);
        }else{
            ErrorHelper.showAdminErrorPage(request,response,rt.getException(),rt.getMessage());
        }
    }

    private void showListView(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ResponseListModel<Category> rt = this.catService.findAll();
        if (rt.isStatus()){
            request.setAttribute("list", rt.getList());
            RequestDispatcher rd = request.getRequestDispatcher("views/admin/category-index.jsp");
            rd.forward(request, response);
        }else{
            ErrorHelper.showAdminErrorPage(request,response,rt.getException(),rt.getMessage());
        }

    }

    private void doAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter(Blog.ColumnTitle);
        String shortContent = request.getParameter(Blog.ColumnShortContent);
        String fullcontent = request.getParameter(Blog.ColumnFullContent);
        String imageUrl = request.getParameter(Blog.ColumnImageUrl);
        int idCategory = Integer.parseInt(request.getParameter(Blog.ColumnIdCategory));

        Blog blog = new Blog(0,title,shortContent,fullcontent,null,imageUrl,0,0,idCategory);
        ResponseModel<Blog> rt=this.blogService.save(blog);
        if (rt.isStatus()){
            request.setAttribute("message",MessageHelper.A_ADD_SUCCESS);
            this.showAddView(request, response);
        }else{
            ErrorHelper.showAdminErrorPage(request,response,rt.getException(),rt.getMessage());
        }
    }

    private void doEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter(Blog.ColumnTitle);
        String shortContent = request.getParameter(Blog.ColumnShortContent);
        String fullcontent = request.getParameter(Blog.ColumnFullContent);
        String imageUrl = request.getParameter(Blog.ColumnImageUrl);
        int idCategory = Integer.parseInt(request.getParameter(Blog.ColumnIdCategory));

        Blog blog = new Blog(id,title,shortContent,fullcontent,null,imageUrl,0,0,idCategory);

        ResponseModel<Blog> rt=this.blogService.update(blog);
        if (rt.isStatus()){
            request.setAttribute("message", MessageHelper.A_UPDATE_SUCCESS);
            this.showEditView(request, response);
        }else{
            ErrorHelper.showAdminErrorPage(request,response,rt.getException(),rt.getMessage());
        }
    }
}
