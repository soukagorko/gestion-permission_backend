package com.backend.controller;

import com.backend.entities.Demande;
import com.backend.service.DemandeService;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

//    @PostMapping("/saveDemande")
//    ResponseEntity<Long> saveDemade(@RequestBody @Validated Demande demande) {
//        return ResponseEntity.ok(this.demandeService.createAndImprimeDemande(demande));
//    }

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

        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(List.of(
                new Demande()
        ), false);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("grade", "Agent de Police");

        JasperReport compileReport = JasperCompileManager
                .compileReport(new FileInputStream("src/main/resources/permission.jrxml"));

        JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, parameters, beanCollectionDataSource);

        // JasperExportManager.exportReportToPdfFile(jasperPrint,
        // System.currentTimeMillis() + ".pdf");

        byte[] data = JasperExportManager.exportReportToPdf(jasperPrint);

        System.err.println(data);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=permission.pdf");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
    }

}
