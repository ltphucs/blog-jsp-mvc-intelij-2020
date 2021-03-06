package com.ltphuc.blog.controller.fe;

import com.ltphuc.blog.common.model.ResponseListModel;
import com.ltphuc.blog.common.service.ResourcesHelper;
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

@WebServlet(name = "HomeController",urlPatterns = "/")
public class HomeController extends HttpServlet {

    private BlogService blogService = new BlogService();
    private CategoryService catService = new CategoryService();
    private AboutService aboutService = new AboutService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{

            ResponseListModel<Category> rtCategory = this.catService.findAll();
            //
            About about = this.aboutService.findTop1().getObject();
            //
            ResponseListModel<Blog> list3Blogs =this.blogService.findTopPublishDate(3);
            ResponseListModel<Blog> list12Blogs =this.blogService.findTopPublishDate(9);
            ResponseListModel<Blog> list5Popular =this.blogService.findTopViews(5);
            //
            if (!list3Blogs.isStatus()||!list12Blogs.isStatus()||!list5Popular.isStatus()){
                ErrorHelper.showUIErrorPage(request,response,null, MessageHelper.E_INPUT_INVALIDATE);
                return;
            }
            //
            request.setAttribute("listCate", rtCategory.getList());
            request.setAttribute("list3Blogs", list3Blogs.getList());
            request.setAttribute("list12Blogs", list12Blogs.getList());
            request.setAttribute("list5Popular", list5Popular.getList());
            request.setAttribute("about", about);
            //
            RequestDispatcher rd = request.getRequestDispatcher("views/fe/ui-home.jsp");
            rd.forward(request, response);
        }catch (Exception ex){
            ErrorHelper.showUIErrorPage(request,response,ex, MessageHelper.E_INPUT_INVALIDATE);
        }
    }
}
