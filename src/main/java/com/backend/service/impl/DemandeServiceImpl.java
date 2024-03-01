package com.backend.service.impl;

import com.backend.entities.Demande;
import com.backend.repositories.DemandeRepository;
import com.backend.service.DemandeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DemandeServiceImpl implements DemandeService {

    private final DemandeRepository demandeRepository;

    @Override
    public Demande createDemande(
            String matricule, String prenom, String nom,
            String grade, String service, String destinationPermission,
            String objet, String lieu, String motif,
            String destination,
            Date dateDebut, int dureePermission) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateDebut);
        calendar.add(Calendar.HOUR_OF_DAY, dureePermission);
        Demande demande = new Demande();
        demande.setMatricule(matricule);
        demande.setPrenom(prenom);
        demande.setNom(nom);
        demande.setGrade(grade);
        demande.setService(service);
        demande.setDestinatairePermission(destinationPermission);
        demande.setObjet(objet);
        demande.setLieu(lieu);
        demande.setMotif(motif);
        demande.setDestination(destination);
        demande.setDateDebut(dateDebut);
        demande.setDureePermission(dureePermission);
        demande.setDateFin(calendar.getTime());
        return demandeRepository.save(demande);
    }
    @Override
    public Long createOrUpdateDemande(Demande demande) {
        return this.demandeRepository.save(demande).getIdDemande();
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
            demandeAMettreAjour.setObjet(demande.getObjet());
            demandeAMettreAjour.setMotif(demande.getMotif());
            demandeAMettreAjour.setDestination(demande.getDestination());
            demandeAMettreAjour.setDateFin(demande.getDateFin());
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

        if (demandeOptional.isEmpty()){
            return null;
        }
        return demandeOptional.get();
    }

    @Override
    public Demande getOnePermission(Long id) {
        //
        Optional<Demande> permissionOptional = this.demandeRepository.findById(id);
        if (permissionOptional.isEmpty()){
            return null;
        }
        return permissionOptional.get();
        //
    }
}
