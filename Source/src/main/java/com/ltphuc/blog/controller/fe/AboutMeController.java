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

@WebServlet(name = "AboutMeController",urlPatterns = "/about")
public class AboutMeController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            CategoryService categoryService = new CategoryService();
            BlogService blogService = new BlogService();
            AboutService aboutService = new AboutService();


            ResponseListModel<Category> all = categoryService.findAll();
            ResponseListModel<Blog> topViews = blogService.findTopViews(5);
            ResponseModel<About> top1 = aboutService.findTop1();
            if (!all.isStatus()||!topViews.isStatus()||!top1.isStatus()){
                ErrorHelper.showUIErrorPage(request,response,null, MessageHelper.E_INPUT_INVALIDATE);
            }else{
                List<Category> listCat = all.getList();
                List<Blog> listTop5Popular = topViews.getList();
                About about = top1.getObject();

                request.setAttribute("about", about);
                request.setAttribute("listCat", listCat);
                request.setAttribute("listTop5Popular", listTop5Popular);

                RequestDispatcher rd=request.getRequestDispatcher("views/fe/ui-about.jsp");
                rd.forward(request, response);
            }
        }catch (Exception ex){
            ErrorHelper.showUIErrorPage(request,response,ex, MessageHelper.E_INPUT_INVALIDATE);
        }
    }
}
