package com.youcode.gestionemployes.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString @EqualsAndHashCode @SuperBuilder
@Entity(name = "utilisateurs") @Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({
        @NamedQuery(name = "Utilisateur.findAll", query = "FROM utilisateurs"),
        @NamedQuery(name = "Utilisateur.findByEmail",
                query = "FROM utilisateurs WHERE email = :email")
})
public class Utilisateur implements Serializable {
    @Serial
    private static final long serialVersionUID = -4794813872135444785L;
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
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
    @Column(name = "gender", length = 1)
    private GenderType gender;
    private Short age;

//    @ElementCollection
//    private List<Adresse> adresses = new HashSet<>();

}
