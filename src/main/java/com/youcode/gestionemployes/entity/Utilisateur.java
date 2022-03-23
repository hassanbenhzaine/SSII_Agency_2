package com.youcode.gestionemployes.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Email;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
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
    @Column(unique = true) @Email(message = "email incorrect")
    private String email; @NotNull(message = "email not null")
    private String firstName;
    private String lastName;
    private String password;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateOfBirth;
    private String phone;
    @Column(columnDefinition = "boolean default false")
    private Boolean status;
    @Enumerated(EnumType.STRING)
    @Column(name = "gender", length = 1)
    private GenderType gender;
}
