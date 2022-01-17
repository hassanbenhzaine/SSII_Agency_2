package com.youcode.gestionemployes.shared;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class PersistenceManager {
    private static final String PERSISTANCE_UNIT_NAME = "GestionEmployesPU";
    private static EntityManagerFactory emf;

    public static EntityManagerFactory getEntityManagerFactory() {
        if (emf == null) emf = Persistence.createEntityManagerFactory(PERSISTANCE_UNIT_NAME);
        return emf;
    }

    public static void close() {
        if (emf != null) emf.close();
    }
}
