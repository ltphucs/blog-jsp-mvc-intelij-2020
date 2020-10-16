package com.ltphuc.blog.common.service;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SecurityHelper {
    public static boolean  checkAuthentication(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();

        String username = (String) session.getAttribute("username");
        if (username == null || username.isEmpty()) {
            return  false;
        }
        return  true;
    }
}
