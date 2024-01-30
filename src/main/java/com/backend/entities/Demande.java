package com.backend.entities;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "demandes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Demande implements Serializable {
//
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_demande")
    private Long idDemande;

    @Column(name = "matricule", length = 20, nullable = false)
    private String matricule;

    @Column(name="prenom", length = 50, nullable = false)
    private String prenom;

    @Column(name="nom", length = 50, nullable = false)
    private String nom;

    @Column(name="service", length = 50, nullable = false)
    private String service;

    @Column(name="grade", length = 50, nullable = false)
    private String grade;

    @Column(name = "destinataire_permission", length = 255, nullable = false)
    private String destinatairePermission;

    @Column(name = "duree_permission", length = 20, nullable = false)
    private String dureePermission;

    @Column(name="lieu", length = 50, nullable = false)
    private String lieu;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateDebut;

    @Column(name = "objet", length = 100, nullable = true)
    private String objet;

    @Column(name = "motif", length = 50, nullable = false)
    private String motif;

    @Column(name = "destination", length = 50, nullable = false)
    private String destination;
    //

}
