package com.youcode.gestionemployes.controller.filter;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if(request.getServletPath().equals("/utilisateur/login")) return true;

        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("currentUtilisateur") != null) return true;
        else response.sendRedirect(request.getContextPath().concat("/utilisateur/login"));

        return false;
    }

}


