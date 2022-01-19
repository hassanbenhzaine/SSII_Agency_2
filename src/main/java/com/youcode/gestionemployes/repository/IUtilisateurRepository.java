package com.youcode.gestionemployes.repository;

import com.youcode.gestionemployes.dao.GenericDAO;
import com.youcode.gestionemployes.entity.Utilisateur;

import java.util.Optional;

public interface IUtilisateurRepository extends GenericDAO<Utilisateur, Integer> {
    Optional<Utilisateur> findByEmail(String email);
}
