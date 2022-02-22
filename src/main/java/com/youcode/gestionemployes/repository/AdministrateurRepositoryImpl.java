package com.youcode.gestionemployes.repository;

import com.youcode.gestionemployes.entity.Administrateur;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AdministrateurRepositoryImpl implements IAdministrateurRepository {
    private final EntityManagerFactory emf;

    @Override
    public void save(Administrateur administrateur) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(administrateur);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    @Override
    public Optional<Administrateur> findById(Integer id) {
        EntityManager em = emf.createEntityManager();
        Optional<Administrateur> administrateur = Optional.ofNullable(em.find(Administrateur.class, id));
        em.close();
        return administrateur;
    }

    @Override
    public Collection<Administrateur> findAll() {
        EntityManager em = emf.createEntityManager();
        List<Administrateur> administateurList = em
                .createNamedQuery("Administrateur.findAll", Administrateur.class)
                .getResultList();
        em.close();
        return administateurList;
    }

    @Override
    public Administrateur update(Administrateur administrateur) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            administrateur = em.merge(administrateur);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return administrateur;
    }

    @Override
    public void delete(Administrateur administrateur) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.remove(em.merge(administrateur));
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}
