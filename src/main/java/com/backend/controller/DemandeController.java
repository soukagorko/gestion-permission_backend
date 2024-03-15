package com.backend.controller;

import com.backend.dtos.DemandePrintDto;
import com.backend.entities.Demande;
import com.backend.service.DemandeService;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/demandes")
@RequiredArgsConstructor
@CrossOrigin("*")
public class DemandeController {
    private final DemandeService demandeService;
    @GetMapping("/")
    ResponseEntity<List<Demande>> getAllDemandes() {
        return ResponseEntity.ok(this.demandeService.getAllDemandes());
    }
    @GetMapping("/{id}")
    ResponseEntity<Demande> getDemandeById(@PathVariable("id") long id) {
        return ResponseEntity.ok(this.demandeService.getOneDemande(id));
    }

    @PostMapping("/")
    ResponseEntity<Long> saveDemade(@RequestBody @Validated Demande demande) {
        return ResponseEntity.ok(this.demandeService.createOrUpdateDemande(demande));
    }
    @PutMapping("/{id}")
    ResponseEntity<Long> updateDemande(@PathVariable("id") long id, @RequestBody @Validated Demande demande) {
        return ResponseEntity.ok(demandeService.editDemande(demande, id));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteDemande(@PathVariable("id") long id) {
        this.demandeService.deleteDemande(id);
        return ResponseEntity.accepted().build();
    }

    @GetMapping(value = "/imprimer/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> imprimerDemande(@PathVariable("id") long id) throws JRException, IOException {

        Demande demandeAImprimer = demandeService.getOneDemande(id);

        if (demandeAImprimer==null) {
            return ResponseEntity.badRequest().build();
        }

        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(List.of(
                DemandePrintDto.fromEntity(demandeAImprimer)
        ), false);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("grade", "Agent de Police");
<<<<<<< HEAD
        JasperReport compileReport = JasperCompileManager
                .compileReport(new FileInputStream("src/main/resources/permission.jrxml"));
=======
        parameters.put("demande_ID",demandeAImprimer.getIdDemande());

        JasperReport compileReport = JasperCompileManager
                .compileReport(new FileInputStream("src/main/resources/permission2.jrxml"));

>>>>>>> 7946a30c57df611b092c326554e537eab31f2ca5
        JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, parameters, beanCollectionDataSource);
        byte[] data = JasperExportManager.exportReportToPdf(jasperPrint);
        System.err.println(data);
        HttpHeaders headers = new HttpHeaders();
<<<<<<< HEAD
        headers.add("Content-Disposition", "inline; filename=permission.pdf");
=======
        String filename = "permission-"+demandeAImprimer.getPrenom()+"-"+demandeAImprimer.getNom()+"-"+demandeAImprimer.getDateCreation()+".pdf";
        headers.add("Content-Disposition", "inline; filename="+filename);

>>>>>>> 7946a30c57df611b092c326554e537eab31f2ca5
        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
    }

}
