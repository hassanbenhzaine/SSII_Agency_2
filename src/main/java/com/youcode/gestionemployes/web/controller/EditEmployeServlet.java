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

@WebServlet(name = "EditEmployeServlet", value = "/edit-employe")
public class EditEmployeServlet extends HttpServlet {
    private static final long serialVersionUID = -4285950653289685199L;
    private EmployeService employeService;
    private TemplateEngine te;

    @Override
    public void init() {
        te = TemplateEngineProvider.getTemplateEngine();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        employeService = new EmployeService();
        Employe employeToEdit = employeService.findById(Integer.valueOf(req.getParameter("id")));

        if (employeToEdit != null) {
            Utilisateur utilisateur = (Utilisateur) req.getSession().getAttribute("utilisateur");
            Context context = new Context();
            context.setVariable("firstName", utilisateur.getFirstName());
            context.setVariable("lastName", utilisateur.getLastName());
            context.setVariable("employeToEdit", employeToEdit);
            te.process("editEmploye", context, resp.getWriter());
        } else resp.sendRedirect(req.getContextPath().concat("/manage-employes"));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        employeService = new EmployeService();
        String id = req.getParameter("id");
        Employe foundEmploye = Employe.builder()
                .id(Integer.valueOf(id)).build();

        if (foundEmploye != null) {
            foundEmploye.setFirstName(req.getParameter("firstName"));
            foundEmploye.setLastName(req.getParameter("lastName"));
            foundEmploye.setPassword(req.getParameter("password"));
            foundEmploye.setDateOfBirth(LocalDate.parse(req.getParameter("dateOfBirth")));
            foundEmploye.setPhone(req.getParameter("phone"));
            foundEmploye.setStatus(Boolean.parseBoolean(req.getParameter("status")));
            foundEmploye.setMatricule(req.getParameter("matricule"));
            foundEmploye.setSalaire(Double.parseDouble(req.getParameter("salaire")));
            foundEmploye.setEmail(req.getParameter("email"));
            employeService.update(foundEmploye);
        }
        resp.sendRedirect(req.getContextPath().concat("/manage-employes"));
    }
}
