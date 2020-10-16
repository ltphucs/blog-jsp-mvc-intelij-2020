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

@WebServlet(name = "BlogCategoryController",urlPatterns = "/category")
public class BlogCategoryController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            // TODO Auto-generated method stub
            //response.getWriter().append("Served at: ").append(request.getContextPath());
            int id = Integer.parseInt(request.getParameter("id"));

            CategoryService categoryService = new CategoryService();
            BlogService blogService = new BlogService();
            AboutService aboutService = new AboutService();

            ResponseModel<Category> resCate= categoryService.findById(id);
            ResponseListModel<Category> resListCate = categoryService.findAll();
            ResponseListModel<Blog> resBlogByCate = blogService.findByCategory(id);
            ResponseListModel<Blog> resTop5Popular = blogService.findTopViews(5);
            ResponseModel<About> resAbout= aboutService.findTop1();

            if (!resCate.isStatus()||!resListCate.isStatus()||!resBlogByCate.isStatus()||!resTop5Popular.isStatus()||!resAbout.isStatus()){
                ErrorHelper.showUIErrorPage(request,response,null, MessageHelper.E_INPUT_INVALIDATE);
            }else{
                Category category = resCate.getObject();
                List<Category> listCat = resListCate.getList();
                List<Blog> listBlogByCatId = resBlogByCate.getList();
                List<Blog> listTop5Popular = resTop5Popular.getList();
                About about = resAbout.getObject();

                request.setAttribute("cat", category);
                request.setAttribute("about", about);
                request.setAttribute("listCat", listCat);
                request.setAttribute("listTop5Popular", listTop5Popular);
                request.setAttribute("listBlog", listBlogByCatId);
                RequestDispatcher rd=request.getRequestDispatcher("views/fe/ui-category.jsp");
                rd.forward(request, response);
            }


        }catch (Exception ex){
            ErrorHelper.showUIErrorPage(request,response,ex, MessageHelper.E_INPUT_INVALIDATE);
        }
    }
}
