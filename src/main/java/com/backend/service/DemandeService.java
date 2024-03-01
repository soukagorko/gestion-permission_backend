package com.backend.service;

import com.backend.entities.Demande;

import java.util.Date;
import java.util.List;

public interface DemandeService {
    //
    Demande createDemande(
            String matricule, String prenom, String nom,
            String grade, String service, String destinationPermission,
            String objet, String lieu, String motif,
            String destination,
            Date dateDebut, int dureePermission);

    Long createOrUpdateDemande(Demande demande);

    List<Demande> getAllDemandes();

    Long editDemande(Demande demande, Long id);

    void deleteDemande(Long id);

    Demande getOneDemande(Long id);
    //
    Demande getOnePermission(Long id);
    //
}
