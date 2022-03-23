package com.youcode.gestionemployes.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Email;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
    @Column(unique = true) @Email(message = "Email invalide")
    private String email;
    @NotNull(message = "Le nom est obligatoire")
    private String firstName;
    @NotNull(message = "Le nom est obligatoire")
    private String lastName;
    @Size(min = 8, max = 20, message = "Mot de passe doit contenir entre 8 et 20 caractères")
    private String password;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateOfBirth;
    @NotNull(message = "Le numéro de téléphone est obligatoire")
    @Min(value = 0, message = "Le numéro de téléphone doit être positif")
    private String phone;
    @Column(columnDefinition = "boolean default false")
    private Boolean status;
    @Enumerated(EnumType.STRING)
    @Column(name = "gender", length = 1)
    private GenderType gender;
}
