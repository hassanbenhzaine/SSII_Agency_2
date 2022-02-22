package com.youcode.gestionemployes.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.context.annotation.Scope;

@Configuration
public class PersistenceConfig {
    private final String PERSISTANCE_UNIT_NAME = "GestionEmployesPU";

    @Bean
    public EntityManagerFactory entityManagerFactory(){
        return Persistence.createEntityManagerFactory(PERSISTANCE_UNIT_NAME);
    }
}
