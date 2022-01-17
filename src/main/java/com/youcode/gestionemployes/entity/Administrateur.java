package com.youcode.gestionemployes.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "administrateurs")
@PrimaryKeyJoinColumn(name = "administrateur_id")
@NamedQuery(name = "Administrateur.findAll", query = " SELECT a FROM administrateurs a")
public class Administrateur extends Utilisateur {
    private String xccc;

    @Override
    public String toString() {
        return "Administrateur{" +
                "xccc='" + xccc + '\'' +
                "} " + super.toString();
    }
}
