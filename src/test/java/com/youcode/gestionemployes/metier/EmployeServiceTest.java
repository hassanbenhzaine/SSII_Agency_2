package com.youcode.gestionemployes.metier;

import com.youcode.gestionemployes.entity.Employe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.Random.class)
class EmployeServiceTest {
    private EmployeService employeService;
    private Employe employe;

    @BeforeEach
    void setUp() {
        employeService = new EmployeService();
        employe = Employe.builder()
                .email("example".concat(
                        String.valueOf(new Random().nextInt(0, 9999))
                ) + "@mail.com")
                .password("passwd".concat(
                        String.valueOf(new Random().nextInt(100, 999))
                ))
                .firstName("firstName".concat(String.valueOf(
                        new Random().nextInt(0, 100)
                )))
                .lastName("lastName".concat(String.valueOf(
                        new Random().nextInt(0, 100)
                )))
                .dateOfBirth(LocalDate.of(
                        new Random().nextInt(1950, 2021),
                        new Random().nextInt(1, 12),
                        new Random().nextInt(1, 28)
                ))
                .phone("06".concat(
                        String.valueOf(new Random().nextInt(0, 99999999))
                ))
                .status(new Random().nextBoolean())
                .matricule("AD" + new Random().nextInt(0, 999999))
                .salaire(new Random().nextDouble(3000, 15000))
                .build();
    }

    @Test
    void save() {
        // given
        // when
        employeService.save(employe);
        // then
        assertEquals(employe, employeService.findById(employe.getId()));
    }

    @Test
    void update() {
        // given
        employeService.save(employe);
        // when
        Employe updatedUtilisateur = employeService.update(employe);
        // then
        assertEquals(employe, updatedUtilisateur);
    }

    @Test
    void delete() {
        // given
        employeService.save(employe);
        // when
        employeService.delete(employe);
        // then
        assertNull(employeService.findById(employe.getId()));
    }

    @Test
    void findById() {
        // given
        employeService.save(employe);
        // when
        Employe foundEmploye = employeService.findById(employe.getId());
        // then
        assertEquals(employe.getId(), foundEmploye.getId());
    }

    @Test
    void findAll() {
        //given
        employeService.save(employe);
        //when
        Collection<? extends Employe> foundEmployeList = employeService.findAll();
        //then
        assertTrue(foundEmployeList.size() > 0);
    }
}