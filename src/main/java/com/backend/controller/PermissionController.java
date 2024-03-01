package com.backend.controller;

import com.backend.dtos.DemandePrintDto;
import com.backend.dtos.PermissionPrintDto;
import com.backend.entities.Demande;
import com.backend.service.DemandeService;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/permissions")
@RequiredArgsConstructor
@CrossOrigin("*")
public class PermissionController {
    //
    private final DemandeService demandeService;
    //
    @GetMapping(value = "/imprimer/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> imprimerPermission(@PathVariable("id") long id) throws JRException, IOException {
        Demande imprimerPermission = demandeService.getOnePermission(id);
        if (imprimerPermission==null) {
            return ResponseEntity.badRequest().build();
        }
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(List.of(
                PermissionPrintDto.fromEntity(imprimerPermission)
        ), false);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("grade", "Agent de Police");
        parameters.put("demande_ID",imprimerPermission.getIdDemande());
        JasperReport compileReport = JasperCompileManager
                .compileReport(new FileInputStream("src/main/resources/permission2-OK.jrxml"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(compileReport, parameters, beanCollectionDataSource);
        byte[] data = JasperExportManager.exportReportToPdf(jasperPrint);
        System.err.println(data);
        HttpHeaders headers = new HttpHeaders();
        String filename = "permission-"+imprimerPermission.getPrenom()+"-"+imprimerPermission.getNom()+"-"+imprimerPermission.getMatricule()+".pdf";
        headers.add("Content-Disposition", "inline; filename="+filename);
        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
    }
    //
}
