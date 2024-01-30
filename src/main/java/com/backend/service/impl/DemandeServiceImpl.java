package com.backend.service.impl;

import com.backend.entities.Demande;
import com.backend.repositories.DemandeRepository;
import com.backend.service.DemandeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DemandeServiceImpl implements DemandeService {

    private final DemandeRepository demandeRepository;

    @Override
    public Long createOrUpdateDemande(Demande demande) {
        return this.demandeRepository.save(demande).getIdDemande();
    }

    @Override
    public Long createAndImprimeDemande(Demande demande) {
        return null;
    }

    @Override
    public List<Demande> getAllDemandes() {
        return this.demandeRepository.findAll();
    }

    @Override
    public Long editDemande(Demande demande, Long id) {

        Optional<Demande> demandeAMettreAjourOptional = null;

        demandeAMettreAjourOptional = this.demandeRepository.findById(id);

        if (demandeAMettreAjourOptional.isPresent()) {

            Demande demandeAMettreAjour = demandeAMettreAjourOptional.get();

            demandeAMettreAjour.setMatricule(demande.getMatricule());
            demandeAMettreAjour.setPrenom(demande.getPrenom());
            demandeAMettreAjour.setNom(demande.getNom());
            demandeAMettreAjour.setService(demande.getService());
            demandeAMettreAjour.setGrade(demande.getGrade());
            demandeAMettreAjour.setDestinatairePermission(demande.getDestinatairePermission());
            demandeAMettreAjour.setDureePermission(demande.getDureePermission());
            demandeAMettreAjour.setLieu(demande.getLieu());
            demandeAMettreAjour.setDateDebut(demande.getDateDebut());
//            demandeAMettreAjour.setObjet(demande.getMotif());
            demandeAMettreAjour.setMotif(demande.getMotif());
            demandeAMettreAjour.setDestination(demande.getDestination());
//            demandeAMettreAjour.setLibelle(demande.getLibelle());
            return this.demandeRepository.save(demandeAMettreAjour).getIdDemande();
        } else {
            return Long.valueOf(0);
        }
    }

    @Override
    public void deleteDemande(Long id) {
        this.demandeRepository.deleteById(id);
    }

    @Override
    public Demande getOneDemande(Long id) {

        Optional<Demande> demandeOptional = this.demandeRepository.findById(id);
        return demandeOptional.get();
    }
}
