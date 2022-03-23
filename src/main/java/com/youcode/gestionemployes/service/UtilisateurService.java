package com.youcode.gestionemployes.metier;

import com.youcode.gestionemployes.entity.Utilisateur;
import com.youcode.gestionemployes.repository.IUtilisateurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
@Service
@RequiredArgsConstructor
public class UtilisateurService {
    private final IUtilisateurRepository IUtilisateurRepository;

    public void save(Utilisateur utilisateur) {
        IUtilisateurRepository.save(utilisateur);
    }

    public Utilisateur update(Utilisateur utilisateur) {
        return IUtilisateurRepository.update(utilisateur);
    }

    public void delete(Utilisateur utilisateur) {
        IUtilisateurRepository.delete(utilisateur);
    }

    public Utilisateur findById(Integer id) {
        return IUtilisateurRepository.findById(id).orElse(null);
    }

    public Collection<? extends Utilisateur> findAll() {
        return IUtilisateurRepository.findAll();
    }

    public Utilisateur findByEmail(String email) {
        return IUtilisateurRepository.findByEmail(email).orElse(null);
    }
}
