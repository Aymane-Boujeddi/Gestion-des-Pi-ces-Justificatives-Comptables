package com.comptable.gd_api.entity;


import com.comptable.gd_api.enums.Role;
import com.comptable.gd_api.enums.StatutUtilisateur;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "utilisateurs")
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    private String nomComplet;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Enumerated(value = EnumType.STRING)
    private StatutUtilisateur status;

    private LocalDateTime dateCreation;


    @ManyToOne
    @JoinColumn(name = "societe_id")
    private Societe societe;

    @PrePersist
    private void dateInit(){
        dateCreation = LocalDateTime.now();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        this.password = encoder.encode(password);
    }

}
