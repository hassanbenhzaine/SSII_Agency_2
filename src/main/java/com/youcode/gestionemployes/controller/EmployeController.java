package com.youcode.gestionemployes.controller;

import com.youcode.gestionemployes.entity.Employe;
import com.youcode.gestionemployes.entity.Utilisateur;
import com.youcode.gestionemployes.metier.EmployeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Collection;

@Controller @RequestMapping("/employe")
@RequiredArgsConstructor
public class EmployeController {
    private final EmployeService employeService;

    @GetMapping("/manage")
    public ModelAndView manageView(){
        ModelAndView mv = new ModelAndView();

        Collection<Employe> foundEmployes = employeService.findAll();
        mv.addObject("employes", foundEmployes);
        mv.setViewName("manageEmployes");
        return mv;
    }

    @GetMapping("/add")
    public String addView() {
        return "addEmploye";
    }

    @PostMapping("/add")
    public String add(Employe employe) {
       employeService.save(employe);
        return "redirect:/employe/manage";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editView(@PathVariable String id){
        ModelAndView mv = new ModelAndView("editEmploye");

        Employe employeToEdit = employeService.findById(Integer.valueOf(id));
        mv.addObject("employeToEdit", employeToEdit);
        return mv;
    }

    @PostMapping("/edit")
    public String edit(Employe employe) {
        employeService.update(employe);
        return "redirect:/employe/manage";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        Employe employeToDelete = Employe.builder()
                .id(Integer.valueOf(id))
                .build();
        employeService.delete(employeToDelete);
        return "redirect:/employe/manage";
    }

    @ModelAttribute
    public void sessionAttributes(Model model, HttpSession httpSession){
        if(!httpSession.isNew()) {
            Utilisateur currentUtilisateur = (Utilisateur) httpSession.getAttribute("utilisateur");
            model.addAttribute("firstName", currentUtilisateur.getFirstName());
            model.addAttribute("lastName", currentUtilisateur.getLastName());
        }
    }

}
