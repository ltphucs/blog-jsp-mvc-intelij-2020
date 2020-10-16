package com.ltphuc.blog.controller.admin;

import com.ltphuc.blog.common.model.ResponseListModel;
import com.ltphuc.blog.common.model.ResponseModel;
import com.ltphuc.blog.common.service.SecurityHelper;
import com.ltphuc.blog.common.ui.ErrorHelper;
import com.ltphuc.blog.common.ui.MessageHelper;
import com.ltphuc.blog.model.About;
import com.ltphuc.blog.model.Blog;
import com.ltphuc.blog.service.AboutService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AboutController",urlPatterns = "/admin-about")
public class AboutController extends HttpServlet {
    AboutService aboutService = new AboutService();

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
        RequestDispatcher rd = request.getRequestDispatcher("views/admin/about-add.jsp");
        rd.forward(request, response);
    }

    private void showEditView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try{
            int id = Integer.parseInt(request.getParameter(About.COLUMN_Id));
            ResponseModel rtAbout = this.aboutService.findById(id);

            if (rtAbout.isStatus()){
                request.setAttribute("about", rtAbout.getObject());

                RequestDispatcher rd = request.getRequestDispatcher("views/admin/about-edit.jsp");
                rd.forward(request, response);

            }else{
                  ErrorHelper.showAdminErrorPage(request, response, rtAbout.getException(), rtAbout.getMessage());

            }
        }catch (Exception ex){
            ErrorHelper.showAdminErrorPage(request, response, ex, MessageHelper.E_INPUT_INVALIDATE);
        }
    }

    private void doDeleteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            int id = Integer.parseInt(request.getParameter(About.COLUMN_Id));

            ResponseModel rt =this.aboutService.delete(new About(id));

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
        ResponseListModel<About> rt = this.aboutService.findAll();
        if (rt.isStatus()){
            request.setAttribute("list", rt.getList());
            RequestDispatcher rd = request.getRequestDispatcher("views/admin/about-index.jsp");
            rd.forward(request, response);
        }else{
            ErrorHelper.showAdminErrorPage(request,response,rt.getException(),rt.getMessage());
        }

    }

    private void doAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            String title = request.getParameter(About.COLUMN_Title);
            String shortContent = request.getParameter(About.COLUMN_ShortContent);
            String fullContent = request.getParameter(About.COLUMN_Content);
            String imageUrl = request.getParameter(About.COLUMN_ImageUrl);

            if (title.isEmpty()||shortContent.isEmpty()||fullContent.isEmpty()||imageUrl.isEmpty()) {
                ErrorHelper.showAdminErrorPage(request, response, null, MessageHelper.E_INPUT_INVALIDATE);
                return;
            }
            About about = new About(0,title,shortContent,fullContent,imageUrl);
            ResponseModel<About> rt=this.aboutService.save(about);
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
            String title = request.getParameter(About.COLUMN_Title);
            String shortContent = request.getParameter(About.COLUMN_ShortContent);
            String fullContent = request.getParameter(About.COLUMN_Content);
            String imageUrl = request.getParameter(About.COLUMN_ImageUrl);
            int id = Integer.parseInt(request.getParameter(About.COLUMN_Id));

            if (title.isEmpty()||shortContent.isEmpty()||fullContent.isEmpty()||imageUrl.isEmpty()) {
                ErrorHelper.showAdminErrorPage(request, response, null, MessageHelper.E_INPUT_INVALIDATE);
                return;
            }

            About about = new About(id,title,shortContent,fullContent,imageUrl);
            ResponseModel<About> rt=this.aboutService.update(about);

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
