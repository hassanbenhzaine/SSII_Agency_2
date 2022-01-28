package com.youcode.gestionemployes.web.controller;

import com.youcode.gestionemployes.entity.Utilisateur;
import com.youcode.gestionemployes.metier.UtilisateurService;
import com.youcode.gestionemployes.shared.TemplateEngineProvider;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.IOException;

@WebServlet(name = "ChangePasswordServlet", value = "/change-password")
public class ChangePasswordServlet extends HttpServlet {
    private static final long serialVersionUID = -2050527651609547061L;
    private UtilisateurService utilisateurService;
    private TemplateEngine te;

    @Override
    public void init() {
        te = TemplateEngineProvider.getTemplateEngine();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
            Utilisateur utilisateur = (Utilisateur) req.getSession().getAttribute("utilisateur");
            Context context = new Context();
            context.setVariable("firstName", utilisateur.getFirstName());
            context.setVariable("lastName", utilisateur.getLastName());
            te.process("changePassword", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        utilisateurService = new UtilisateurService();
        Context context = new Context();
        Utilisateur utilisateur = (Utilisateur) req.getSession().getAttribute("utilisateur");
        String newPassword = req.getParameter("password");
        String repeatPassword = req.getParameter("repeatPassword");
        String oldPassword = req.getParameter("oldPassword");

        if (oldPassword.equals(utilisateur.getPassword())) {
            if (newPassword.equals(repeatPassword)) {
                utilisateur.setPassword(newPassword);
                utilisateurService.update(utilisateur);
                resp.sendRedirect(req.getContextPath().concat("/manage-employes"));
            } else {
                context.setVariable("error", "Les nouveaux mots de passe ne correspondent pas");
                te.process("changePassword", context, resp.getWriter());
                }
            } else {
            context.setVariable("error", "Le mot de passe ne correspondent pas a l'ancien");
            te.process("changePassword", context, resp.getWriter());
        }
    }
}
