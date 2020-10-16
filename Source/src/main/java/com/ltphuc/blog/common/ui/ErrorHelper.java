package com.ltphuc.blog.common.ui;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ErrorHelper  {
    public static void showAdminErrorPage(HttpServletRequest request, HttpServletResponse response, Exception ex, String message) throws ServletException, IOException {
        request.setAttribute("ex",ex);
        request.setAttribute("message",message);
        RequestDispatcher rd = request.getRequestDispatcher("views/admin/error.jsp");
        rd.forward(request, response);
    }

    public static void showUIErrorPage(HttpServletRequest request, HttpServletResponse response, Exception ex, String message) throws ServletException, IOException {
        request.setAttribute("ex",ex);
        request.setAttribute("message",message);
        RequestDispatcher rd = request.getRequestDispatcher("views/fe/error.jsp");
        rd.forward(request, response);
    }
}
