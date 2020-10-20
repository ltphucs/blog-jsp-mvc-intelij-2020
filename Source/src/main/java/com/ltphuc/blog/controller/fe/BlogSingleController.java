package com.ltphuc.blog.controller.fe;

import com.ltphuc.blog.common.model.ResponseListModel;
import com.ltphuc.blog.common.model.ResponseModel;
import com.ltphuc.blog.common.ui.ErrorHelper;
import com.ltphuc.blog.common.ui.MessageHelper;
import com.ltphuc.blog.model.About;
import com.ltphuc.blog.model.Blog;
import com.ltphuc.blog.model.Category;
import com.ltphuc.blog.service.AboutService;
import com.ltphuc.blog.service.BlogService;
import com.ltphuc.blog.service.CategoryService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "BlogSingleController",urlPatterns = "/blog-single")
public class BlogSingleController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            int id = Integer.parseInt(request.getParameter("id"));

            CategoryService categoryService = new CategoryService();
            BlogService blogService = new BlogService();
            AboutService aboutService = new AboutService();

            ResponseListModel<Category> categoryServiceAll = categoryService.findAll();
            ResponseModel<Blog> blogServiceById = blogService.findById(id);
            ResponseListModel<Blog> blogServiceTopViews = blogService.findTopViews(5);

            if (!categoryServiceAll.isStatus()||!blogServiceById.isStatus()||!blogServiceTopViews.isStatus()){
                ErrorHelper.showUIErrorPage(request,response,null, MessageHelper.E_INPUT_INVALIDATE);
                return;
            }

            List<Category> listCat = categoryServiceAll.getList();
            Blog blog = blogServiceById.getObject();
            List<Blog> listTop5Popular = blogServiceTopViews.getList();
            About about = aboutService.findTop1().getObject();

            ResponseListModel<Blog> blogServiceByCategory = blogService.findByCategory(blog.getIdCategory(), 3);
            if (!blogServiceByCategory.isStatus()){
                ErrorHelper.showUIErrorPage(request,response,null, MessageHelper.E_INPUT_INVALIDATE);
                return;
            }
            List<Blog> listTop3Related = blogServiceByCategory.getList();

            blogService.incView(id);

            request.setAttribute("blog", blog);
            request.setAttribute("about", about);
            request.setAttribute("listCat", listCat);
            request.setAttribute("listTop5Popular", listTop5Popular);
            request.setAttribute("listTop3Related", listTop3Related);

            RequestDispatcher rd=request.getRequestDispatcher("views/fe/ui-details.jsp");
            rd.forward(request, response);
        }catch (Exception ex){
            ErrorHelper.showUIErrorPage(request,response,ex, MessageHelper.E_INPUT_INVALIDATE);
        }

    }
}
