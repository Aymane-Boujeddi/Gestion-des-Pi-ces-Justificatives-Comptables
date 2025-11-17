package com.comptable.gd_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Societe {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String raisonSociale;

    private String ice;

    private String adresse;

    private String telephone;

    private String emailContact;

    @OneToMany(mappedBy = "societe")
    private List<Utilisateur> utilisateurs;

    @OneToMany(mappedBy = "societe")
    private List<Document> documents;

}
