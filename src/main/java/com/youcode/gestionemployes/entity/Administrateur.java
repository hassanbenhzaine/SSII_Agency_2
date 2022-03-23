package com.youcode.gestionemployes.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serial;


@Getter @Setter @AllArgsConstructor @ToString @EqualsAndHashCode @SuperBuilder
@Entity(name = "administrateurs") @PrimaryKeyJoinColumn(name = "administrateur_id")
@NamedQuery(name = "Administrateur.findAll", query = "FROM administrateurs")
public class Administrateur extends Utilisateur {
    @Serial
    private static final long serialVersionUID = 117454118301540503L;
}
