package com.youcode.gestionemployes.metier;

import com.youcode.gestionemployes.entity.GenderType;
import com.youcode.gestionemployes.entity.Utilisateur;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.Random.class)
class UtilisateurServiceTest {
    private UtilisateurService utilisateurService;
    private Utilisateur utilisateur;

    @BeforeEach
    void setUp() {
        utilisateurService = new UtilisateurService();
        utilisateur = Utilisateur.builder()
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
                .gender(GenderType.values()[new Random().nextInt(GenderType.values().length)])
                .age((short) new Random().nextInt(18, 100))
                .build();
    }

    @Test
    void save() {
        // given
        // when
        utilisateurService.save(utilisateur);
        // then
        assertEquals(utilisateur, utilisateurService.findById(utilisateur.getId()));
    }

    @Test
    void update() {
        // given
        utilisateurService.save(utilisateur);
        utilisateur.setPassword("updatedPassword");
        utilisateur.setFirstName("updatedFirstName");
        utilisateur.setLastName("updatedLastName");
        // when
        Utilisateur updatedUtilisateur = utilisateurService.update(utilisateur);
        // then
        assertEquals(utilisateur, updatedUtilisateur);
    }

    @Test
    void delete() {
        // given
        utilisateurService.save(utilisateur);
        // when
        utilisateurService.delete(utilisateur);
        // then
        assertNull(utilisateurService.findById(utilisateur.getId()));
    }

    @Test
    void findById() {
        // given
        utilisateurService.save(utilisateur);
        // when
        Utilisateur foundUtilisateur = utilisateurService.findById(utilisateur.getId());
        // then
        assertEquals(utilisateur.getId(), foundUtilisateur.getId());
    }

    @Test
    void findAll() {
        //given
        utilisateurService.save(utilisateur);
        //when
        Collection<? extends Utilisateur> foundUtilisateurList = utilisateurService.findAll();
        //then
        assertTrue(foundUtilisateurList.size() > 0);
    }

    @Test
    void findByEmail() {
        //given
        utilisateurService.save(utilisateur);
        // when
        Utilisateur foundUtilisateur = utilisateurService.findByEmail(utilisateur.getEmail());
        System.out.println(foundUtilisateur);
        // then
        assertEquals(utilisateur.getEmail(), foundUtilisateur.getEmail());
    }
}