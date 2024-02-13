package com.backend.dtos;

import com.backend.entities.Demande;
import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author masta
 * @since 06/02/2024
 *
 * Cette classe est uniquement utilisée pour encapsuler les données à imprimer.
 * A savoir les données à envoyer au jaspert report pour l'impression de la demande.
 * Elle doit contenir excatement les memes noms des attributs de la table, et non ceux de la classe.
 *
 * Par conséquent cette classe permet de transformer les noms des attributs.
 * Exemple: dateDebut -> date_debut.
 */
@Getter
@Setter
@AllArgsConstructor
@Builder
public class DemandePrintDto {

    private String matricule;

    private String prenom;

    private String nom;

    private String service;

    private String grade;

    private String destinataire_permission;

    private String duree_permission;

    private String lieu;

    private Date date_debut;

    private Date date_fin;

    private Date date_creation;

    private String objet;

    private String motif;

    private String destination;

    public static DemandePrintDto fromEntity(Demande demande) {

        return DemandePrintDto.builder()
                .prenom(demande.getPrenom())
                .nom(demande.getNom())
                .matricule(demande.getMatricule())
                .service(demande.getService())
                .grade(demande.getGrade())
                .date_debut(demande.getDateDebut())
                .date_fin(demande.getDateFin())
                .date_creation(demande.getDateCreation())
                .duree_permission(demande.getDureePermission())
                .destinataire_permission(demande.getDestinatairePermission())
                .motif(demande.getMotif())
                .destination(demande.getDestination())
                .objet(demande.getObjet())
                .lieu(demande.getLieu())
                .build();

    }
}
