package com.youcode.gestionemployes.web.controller;

import com.youcode.gestionemployes.entity.Utilisateur;
import com.youcode.gestionemployes.metier.UtilisateurService;
import com.youcode.gestionemployes.shared.TemplateEngineProvider;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.IOException;
import java.util.regex.Pattern;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = -7981866110922019133L;
    private UtilisateurService utilisateurService;
    private TemplateEngine te;

    @Override
    public void init(ServletConfig servletConfig) {
        te = TemplateEngineProvider.getTemplateEngine();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        utilisateurService = new UtilisateurService();
        Context context = new Context();
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        Pattern emailPattern = Pattern
                .compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Pattern passwordPattern = Pattern
                .compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", Pattern.CASE_INSENSITIVE);

        if (!emailPattern.matcher(email).matches() && !passwordPattern.matcher(password).matches()) {
            context.setVariable("error", "Email ou mot de passe invalide");
            te.process("login", context, resp.getWriter());
        } else {
            System.out.println(email);
            Utilisateur utilisateur = utilisateurService.findByEmail(email);
            System.out.println(utilisateur);
            if (utilisateur != null && utilisateur.getPassword().equals(password)) {
                req.getSession().setAttribute("utilisateur", utilisateur);
                resp.sendRedirect(req.getContextPath().concat("/manage-employes"));
            } else {
                context.setVariable("error", "Email ou mot de passe incorrect");
                te.process("login", context, resp.getWriter());
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        te.process("login", new Context(), resp.getWriter());
    }
}