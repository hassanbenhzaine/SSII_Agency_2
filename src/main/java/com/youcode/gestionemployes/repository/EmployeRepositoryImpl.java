package com.youcode.gestionemployes.repository;

import com.youcode.gestionemployes.entity.Employe;
import com.youcode.gestionemployes.shared.PersistenceManager;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class EmployeRepositoryImpl implements EmployeRepository {
    private final EntityManagerFactory emf = PersistenceManager.getEntityManagerFactory();

    @Override
    public Employe save(Employe employe) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(employe);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return employe;
    }

    @Override
    public Optional<Employe> findById(Integer id) {
        EntityManager em = emf.createEntityManager();
        Optional<Employe> employe = Optional.ofNullable(em.find(Employe.class, id));
        em.close();
        return employe;
    }

    @Override
    public Collection<Employe> findAll() {
        EntityManager em = emf.createEntityManager();
        List<Employe> employeList = em
                .createNamedQuery("Employe.findAll", Employe.class)
                .getResultList();
        em.close();
        return employeList;
    }

    @Override
    public Employe update(Employe employe) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(employe);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return employe;
    }

    @Override
    public void delete(Employe employe) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.remove(em.contains(employe) ? employe : em.merge(employe));
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}
