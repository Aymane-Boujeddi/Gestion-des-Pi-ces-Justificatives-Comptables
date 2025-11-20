package com.comptable.gd_api.config;

import com.comptable.gd_api.entity.Societe;
import com.comptable.gd_api.entity.Utilisateur;
import com.comptable.gd_api.enums.Role;
import com.comptable.gd_api.enums.StatutUtilisateur;
import com.comptable.gd_api.repository.SocieteRepository;
import com.comptable.gd_api.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataCreation implements CommandLineRunner {

    @Autowired
    private SocieteRepository societeRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Override
    public void run(String... args) throws Exception {
        // Create Societes
        if (societeRepository.count() == 0) {
            Societe societe1 = Societe.builder()
                    .raisonSociale("ABC Entreprise")
                    .ice("123456789")
                    .adresse("123 Rue Mohammed V, Casablanca")
                    .telephone("0522123456")
                    .emailContact("contact@abc-entreprise.ma")
                    .build();
            societeRepository.save(societe1);

            Societe societe2 = Societe.builder()
                    .raisonSociale("XYZ Corporation")
                    .ice("987654321")
                    .adresse("456 Avenue Hassan II, Rabat")
                    .telephone("0537654321")
                    .emailContact("info@xyz-corp.ma")
                    .build();
            societeRepository.save(societe2);

            // Create Users
            Utilisateur comptable = Utilisateur.builder()
                    .email("comptable@example.com")
                    .password("password123")
                    .nomComplet("Ahmed Benali")
                    .role(Role.COMPTABLE)
                    .status(StatutUtilisateur.ACTIF)
                    .build();
            utilisateurRepository.save(comptable);

            Utilisateur userSociete = Utilisateur.builder()
                    .email("user@abc-entreprise.ma")
                    .password("password456")
                    .nomComplet("Fatima Alami")
                    .role(Role.SOCIETE)
                    .status(StatutUtilisateur.ACTIF)
                    .societe(societe1)
                    .build();
            utilisateurRepository.save(userSociete);
        }
    }
}
