package com.youcode.gestionemployes.service;

import com.youcode.gestionemployes.entity.Employe;
import com.youcode.gestionemployes.entity.Utilisateur;
import com.youcode.gestionemployes.repository.IEmployeRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
@Service
public class EmployeService extends UtilisateurService {
    private final IEmployeRepository IEmployeRepository;

    public EmployeService(com.youcode.gestionemployes.repository.IUtilisateurRepository IUtilisateurRepository, com.youcode.gestionemployes.repository.IEmployeRepository iEmployeRepository) {
        super(IUtilisateurRepository);
        IEmployeRepository = iEmployeRepository;
    }

    @Override
    public void delete(Utilisateur employe) {
        IEmployeRepository.delete((Employe) employe);
    }

    @Override
    public void save(Utilisateur employe) {
        IEmployeRepository.save((Employe) employe);
    }

    @Override
    public Employe update(Utilisateur employe) {
        return IEmployeRepository.update((Employe) employe);
    }

    @Override
    public Employe findById(Integer id) {
        return IEmployeRepository.findById(id).orElse(null);
    }

    @Override
    public Collection<Employe> findAll() {
        return IEmployeRepository.findAll();
    }

}
