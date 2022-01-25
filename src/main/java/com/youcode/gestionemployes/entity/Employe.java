package com.youcode.gestionemployes.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "employes")
@PrimaryKeyJoinColumn(name = "employe_id")
@NamedQuery(name = "Employe.findAll", query = "FROM employes")
public class Employe extends Utilisateur {
    @Serial
    private static final long serialVersionUID = -4144001680744514884L;
    @Column(unique = true)
    private String matricule;
    private Double salaire;
}
