package com.youcode.gestionemployes.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Max;
import java.io.Serial;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString @EqualsAndHashCode @SuperBuilder
@Entity(name = "employes") @PrimaryKeyJoinColumn(name = "employe_id")
@NamedQuery(name = "Employe.findAll", query = "FROM employes")
public class Employe extends Utilisateur {
    @Serial
    private static final long serialVersionUID = -4144001680744514884L;
    @Column(unique = true)
    private String matricule;
    @Max(value = 1000L, message = "over salary")
    private Double salaire;
}
