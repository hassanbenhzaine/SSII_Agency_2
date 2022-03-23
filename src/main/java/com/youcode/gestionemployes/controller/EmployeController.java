package com.youcode.gestionemployes.controller;

import com.youcode.gestionemployes.entity.Employe;
import com.youcode.gestionemployes.service.EmployeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Collection;

@Controller @RequestMapping("/employe") @SessionAttributes("currentUtilisateur")
@RequiredArgsConstructor
public class EmployeController {
    private final EmployeService employeService;

    @GetMapping({"/manage", ""})
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
    public String add(@Valid Employe employe, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()){
            model.addAttribute("error", bindingResult.getFieldError().getDefaultMessage());
            return "addEmploye";
        }
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
    public String edit(Employe employe, Errors errors, Model model) {
        employeService.update(employe);
//        if (errors.hasErrors()) {
//            model.addAttribute("error", "there is an error");
//            return "redirect:/employe/edit/" + employe.getId();
//        }
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



}
