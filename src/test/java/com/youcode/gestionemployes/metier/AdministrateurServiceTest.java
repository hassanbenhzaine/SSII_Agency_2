package com.youcode.gestionemployes.metier;

import com.youcode.gestionemployes.entity.Administrateur;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.Random.class)
class AdministrateurServiceTest {
    private AdministrateurService administrateurService;
    private Administrateur administrateur;

    @BeforeEach
    void setUp() {
        administrateurService = new AdministrateurService();
        administrateur = Administrateur.builder()
                .email("example".concat(
                        String.valueOf(new Random().nextInt(0, 9999))
                ) + "@mail.com")
                .password("passwd".concat(
                        String.valueOf(new Random().nextInt(100, 999))
                ))
                .firstName("firstName")
                .lastName("lastName")
                .dateOfBirth(LocalDate.of(
                        new Random().nextInt(1950, 2021),
                        new Random().nextInt(1, 12),
                        new Random().nextInt(1, 28)
                ))
                .phone("06".concat(
                        String.valueOf(new Random().nextInt(0, 99999999))
                ))
                .status(new Random().nextBoolean())
                .xccc("azd")
                .build();
    }

    @Test
    void save() {
        // given
        // when
        administrateurService.save(administrateur);
        // then
        assertEquals(administrateur, administrateurService.findById(administrateur.getId()));
    }

    @Test
    void update() {
        // given
        administrateurService.save(administrateur);
        administrateur.setPassword("updatedPassword");
        administrateur.setFirstName("updatedFirstName");
        administrateur.setXccc("abc");
        // when
        Administrateur updatedAdministrateur = administrateurService.update(administrateur);
        // then
        assertEquals(administrateur, updatedAdministrateur);
    }

    @Test
    void delete() {
        // given
        administrateurService.save(administrateur);
        // when
        administrateurService.delete(administrateur);
        // then
        assertNull(administrateurService.findById(administrateur.getId()));
    }

    @Test
    void findById() {
        // given
        administrateurService.save(administrateur);
        // when
        Administrateur foundAdministrateur = administrateurService.findById(administrateur.getId());
        // then
        assertEquals(administrateur.getId(), foundAdministrateur.getId());
    }

    @Test
    void findAll() {
        //given
        administrateurService.save(administrateur);
        //when
        Collection<? extends Administrateur> foundAdministrateurList = administrateurService.findAll();
        //then
        assertTrue(foundAdministrateurList.size() > 0);
    }
}