package com.backend.dtos;

import com.backend.entities.Demande;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class PermissionPrintDto {
    //
    private Long idDemande;

    private String matricule;

    private String prenom;

    private String nom;

    private String service;

    private String grade;

    private String destinataire_permission;

    private String lieu;

    private Date date_debut;

    private int duree_permission;

    private Date date_fin;

    private String objet;

    private String motif;

    private String destination;

    private Date created_at;

    public static PermissionPrintDto fromEntity(Demande demande) {

        return PermissionPrintDto.builder()
                .idDemande(demande.getIdDemande())
                .prenom(demande.getPrenom())
                .nom(demande.getNom())
                .matricule(demande.getMatricule())
                .service(demande.getService())
                .grade(demande.getGrade())
                .date_debut(demande.getDateDebut())
                .duree_permission(demande.getDureePermission())
                .date_fin(demande.getDateFin())
                .destinataire_permission(demande.getDestinatairePermission())
                .motif(demande.getMotif())
                .destination(demande.getDestination())
                .objet(demande.getObjet())
                .lieu(demande.getLieu())
                .created_at(demande.getCreated_at())
                .build();

    }
    //
}
