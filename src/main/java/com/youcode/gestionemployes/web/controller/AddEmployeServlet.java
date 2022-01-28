package com.youcode.gestionemployes.web.controller;

import com.youcode.gestionemployes.entity.Employe;
import com.youcode.gestionemployes.entity.Utilisateur;
import com.youcode.gestionemployes.metier.EmployeService;
import com.youcode.gestionemployes.shared.TemplateEngineProvider;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name = "AddEmployeServlet", value = "/add-employe")
public class AddEmployeServlet extends HttpServlet {
    private static final long serialVersionUID = 1863227963442669583L;
    private TemplateEngine te;
    private EmployeService employeService;

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
        te.process("addEmploye", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        employeService = new EmployeService();
        Employe employe = Employe.builder()
                .firstName(req.getParameter("firstName"))
                .lastName(req.getParameter("lastName"))
                .email(req.getParameter("email"))
                .password(req.getParameter("password"))
                .dateOfBirth(LocalDate.parse(req.getParameter("dateOfBirth")))
                .phone(req.getParameter("phone"))
                .status(Boolean.parseBoolean(req.getParameter("status")))
                .matricule(req.getParameter("matricule"))
                .salaire(Double.parseDouble(req.getParameter("salaire")))
                .build();

        employeService.save(employe);
        resp.sendRedirect(req.getContextPath().concat("/manage-employes"));
    }
}
