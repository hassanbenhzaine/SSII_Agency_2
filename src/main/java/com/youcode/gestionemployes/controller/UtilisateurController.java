package com.youcode.gestionemployes.controller;

import com.youcode.gestionemployes.entity.Utilisateur;
import com.youcode.gestionemployes.service.UtilisateurService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller @RequestMapping("/utilisateur") @SessionAttributes("currentUtilisateur")
@RequiredArgsConstructor
public class UtilisateurController {
    private final UtilisateurService utilisateurService;

    @GetMapping("/login")
    public String loginView() {
        return "login";
    }

    @PostMapping("/login")
    public ModelAndView login(Utilisateur utilisateur, HttpSession httpSession) {
        ModelAndView mv = new ModelAndView();
        Utilisateur foundUtilisateur = utilisateurService.findByEmail(utilisateur.getEmail());
        if (foundUtilisateur != null && foundUtilisateur.getPassword().equals(utilisateur.getPassword())) {
            httpSession.setAttribute("currentUtilisateur", foundUtilisateur);
            mv.setViewName("redirect:/employe/manage");
        } else {
            mv.addObject("error", "Email ou mot de passe incorrect");
            mv.setViewName("login");
        }
        return mv;
    }

    @GetMapping("/changepassword")
    public String changePasswordView() {
        return "changePassword";
    }

    @PostMapping("/changepassword")
    public ModelAndView changePasswordView(@RequestParam Map<String, String> passwords, HttpSession httpSession) {
        ModelAndView mv = new ModelAndView();
        Utilisateur currentUtilisateur = (Utilisateur) httpSession.getAttribute("currentUtilisateur");
        String oldPassword = passwords.get("oldPassword");
        String newPassword = passwords.get("password");
        String repeatPassword = passwords.get("repeatPassword");
        System.out.println(passwords);

        if (oldPassword.equals(currentUtilisateur.getPassword())) {
            if (newPassword.equals(repeatPassword)) {
                currentUtilisateur.setPassword(newPassword);
                utilisateurService.update(currentUtilisateur);
                mv.setViewName("redirect:/employe/manage");
            } else {
                mv.addObject("error", "Les nouveaux mots de passe ne correspondent pas");
                mv.setViewName("changePassword");
            }
        } else {
            mv.addObject("error", "Le mot de passe ne correspondent pas a l'ancien");
            mv.setViewName("changePassword");
        }

        return mv;
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
        httpSession.setAttribute("currentUtilisateur", null);
        httpSession.invalidate();
       return "redirect:login";
    }

}
