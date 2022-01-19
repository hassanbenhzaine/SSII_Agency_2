package com.youcode.gestionemployes.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "utilisateurs")
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({
        @NamedQuery(name = "Utilisateur.findAll", query = "SELECT a FROM utilisateurs a"),
        @NamedQuery(name = "Utilisateur.findByEmail",
                query = "SELECT a FROM utilisateurs a WHERE a.email = :email")
})
public class Utilisateur {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(updatable = false)
    private Integer id;
    @Column(unique = true)
    private String email;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String password;
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    @Column
    private String phone;
    @Column(columnDefinition = "boolean default false")
    private Boolean status;
    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private GenderType genderType;
    private Short age;

//    @ElementCollection
//    private List<Adresse> adresses = new HashSet<>();

}
