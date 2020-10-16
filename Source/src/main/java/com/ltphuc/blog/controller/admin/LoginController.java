package com.ltphuc.blog.controller.admin;

import com.ltphuc.blog.common.model.ResponseModel;
import com.ltphuc.blog.common.ui.ErrorHelper;
import com.ltphuc.blog.model.User;
import com.ltphuc.blog.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LoginController", urlPatterns = "/login")
public class LoginController extends HttpServlet {
    UserService userService = new UserService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username= request.getParameter("email");
        String password = request.getParameter("password");

        ResponseModel<User> rt = userService.findByUsernamePassword(username,password);
        if (rt.isStatus()){
            User user = rt.getObject();
            //Dang nhap thanh cong
            if (user!=null) {
                HttpSession session = request.getSession();
                session.setAttribute("username", user.getUsername());
                session.setAttribute("user", user);
                response.sendRedirect("admin-dashboard");
            }else {
                //Dang nhap khong thanh cong
                request.setAttribute("message", "Username, password could wrong, pls try again!");
                this.showLogin(request, response);
            }
        }else{
            ErrorHelper.showAdminErrorPage(request,response,rt.getException(),rt.getMessage());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null)
            action = "login";
        switch (action) {
            case "login":
                this.showLogin(request, response);
                break;
            case "logout":
                this.showLogout(request, response);
                break;

        }
    }

    private void showLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("views/admin/login.jsp");
        rd.forward(request, response);
    }

    private void showLogout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        //XOA HET CAC SESSION DE TRA VE LOGIN
        session.invalidate();
        RequestDispatcher rd = request.getRequestDispatcher("views/admin/login.jsp");
        rd.forward(request, response);
    }
}
