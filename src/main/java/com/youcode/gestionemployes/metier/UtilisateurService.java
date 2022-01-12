package com.youcode.gestionemployes.metier;

import com.youcode.gestionemployes.entity.Utilisateur;
import com.youcode.gestionemployes.repository.UtilisateurRepository;
import com.youcode.gestionemployes.repository.UtilisateurRepositoryImpl;

import java.util.Collection;

public class UtilisateurService {
    private final UtilisateurRepository utilisateurRepository;

    public UtilisateurService() {
        utilisateurRepository = new UtilisateurRepositoryImpl();
    }

    public Utilisateur save(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }

    public Utilisateur update(Utilisateur utilisateur) {
        return utilisateurRepository.update(utilisateur);
    }

    public void delete(Utilisateur utilisateur) {
        utilisateurRepository.delete(utilisateur);
    }

    public Utilisateur findById(Integer id) {
        return utilisateurRepository.findById(id).orElse(null);
    }

    public Collection<? extends Utilisateur> findAll() {
        return utilisateurRepository.findAll();
    }

    public Utilisateur findByEmail(String email) {
        return utilisateurRepository.findByEmail(email).orElse(null);
    }
}
