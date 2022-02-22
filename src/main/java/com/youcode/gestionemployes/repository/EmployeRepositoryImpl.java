package com.youcode.gestionemployes.repository;

import com.youcode.gestionemployes.entity.Employe;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class EmployeRepositoryImpl implements IEmployeRepository {
    private final EntityManagerFactory emf;

    @Override
    public void save(Employe employe) {
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
            employe = em.merge(employe);
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
            em.remove(em.merge(employe));
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}
