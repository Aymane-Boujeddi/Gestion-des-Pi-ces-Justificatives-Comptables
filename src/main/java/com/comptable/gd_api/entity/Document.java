package com.comptable.gd_api.entity;

import com.comptable.gd_api.enums.StatutDocument;
import com.comptable.gd_api.enums.Type;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Document {
    @Id
    private Long id;

    private String numeroPiece;

    @Enumerated(value = EnumType.STRING)
    private Type type;

    @Enumerated(value = EnumType.STRING)
    private StatutDocument statutDocument;

    private LocalDate dateValidation;

    private String commentaireComptable;

    private String categorieComptable;

    private LocalDate datePiece;

    private double montant;

    private String Fournisseur;

    private String fichierPiece;

    @ManyToOne
    @JoinColumn(name = "societe_id")
    private Societe societe;

    private LocalDateTime dateCreation;

    private LocalDateTime dateModification;

    @PrePersist
    private void dateInit(){
        dateCreation = LocalDateTime.now();
        dateModification = LocalDateTime.now();
    }

    @PreUpdate
    private void dateUpdate(){
        dateModification = LocalDateTime.now();
    }

}
