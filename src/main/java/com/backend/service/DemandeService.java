package com.backend.service;

import com.backend.entities.Demande;

import java.util.List;

public interface DemandeService {

    Long createOrUpdateDemande(Demande demande);

    Long createAndImprimeDemande(Demande demande);

    List<Demande> getAllDemandes();

    Long editDemande(Demande demande, Long id);

    void deleteDemande(Long id);

    Demande getOneDemande(Long id);
}
