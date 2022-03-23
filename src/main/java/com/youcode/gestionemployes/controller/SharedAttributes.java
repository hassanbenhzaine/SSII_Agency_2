package com.youcode.gestionemployes.controller;

import com.youcode.gestionemployes.entity.Utilisateur;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpSession;

@ControllerAdvice
public class SharedAttributes {
    @ModelAttribute
    public void currentUtilisateur(Model model, HttpSession httpSession){
        Utilisateur currentUtilisateur =
                (Utilisateur) httpSession.getAttribute("currentUtilisateur");

        if(!httpSession.isNew() && currentUtilisateur != null) {
            model.addAttribute("firstName", currentUtilisateur.getFirstName());
            model.addAttribute("lastName", currentUtilisateur.getLastName());
            model.addAttribute("currentUtilisateur", currentUtilisateur);
        }
    }
}
