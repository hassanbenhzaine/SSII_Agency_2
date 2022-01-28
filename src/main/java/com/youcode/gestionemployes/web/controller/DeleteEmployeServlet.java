package com.youcode.gestionemployes.web.controller;

import com.youcode.gestionemployes.entity.Employe;
import com.youcode.gestionemployes.metier.UtilisateurService;
import com.youcode.gestionemployes.shared.TemplateEngineProvider;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.TemplateEngine;

import java.io.IOException;

@WebServlet(name = "DeleteEmployeServlet", value = "/delete-employe")
public class DeleteEmployeServlet extends HttpServlet {
    private static final long serialVersionUID = -6380929559804098125L;
    private UtilisateurService utilisateurService;
    private TemplateEngine te;

    @Override
    public void init() {
        te = TemplateEngineProvider.getTemplateEngine();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Integer employeId = Integer.valueOf(req.getParameter("id"));
        utilisateurService = new UtilisateurService();
        Employe employe = (Employe) utilisateurService.findById(employeId);
        if (employe != null) {
            utilisateurService.delete(employe);

        }
        resp.sendRedirect(req.getContextPath().concat("/manage-employes"));
    }
}
