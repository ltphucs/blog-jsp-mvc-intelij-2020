package com.ltphuc.blog.controller.admin;

import com.ltphuc.blog.common.service.SecurityHelper;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DashboardController", urlPatterns = "/admin-dashboard")
public class DashboardController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!SecurityHelper.checkAuthentication(request,response)){
            response.sendRedirect("login");
        }else{
            RequestDispatcher rd = request.getRequestDispatcher("views/admin/dashboard.jsp");
            rd.forward(request, response);
        }
    }
}
