package com.youcode.gestionemployes.metier;

import com.youcode.gestionemployes.entity.Administrateur;
import com.youcode.gestionemployes.entity.Utilisateur;
import com.youcode.gestionemployes.repository.AdministrateurRepositoryImpl;
import com.youcode.gestionemployes.repository.IAdministrateurRepository;

import java.util.Collection;

public class AdministrateurService extends UtilisateurService {
    private final IAdministrateurRepository IAdministrateurRepository;

    public AdministrateurService() {
        IAdministrateurRepository = new AdministrateurRepositoryImpl();
    }

    @Override
    public void delete(Utilisateur administrateur) {
        IAdministrateurRepository.delete((Administrateur) administrateur);
    }

    @Override
    public void save(Utilisateur administrateur) {
        IAdministrateurRepository.save((Administrateur) administrateur);
    }

    @Override
    public Administrateur update(Utilisateur administrateur) {
        return IAdministrateurRepository.update((Administrateur) administrateur);
    }

    @Override
    public Administrateur findById(Integer id) {
        return IAdministrateurRepository.findById(id).orElse(null);
    }

    @Override
    public Collection<Administrateur> findAll() {
        return IAdministrateurRepository.findAll();
    }

}
