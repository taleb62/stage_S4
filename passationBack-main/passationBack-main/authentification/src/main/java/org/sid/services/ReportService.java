package org.sid.services;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.sid.entites.ProcedurePaa;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.Date;

@Service
public class ReportService {

    public byte[] generateProcedureReport(ProcedurePaa procedurePaa) throws JRException {
        // Imprimer les valeurs de procedurePaa pour vérifier leur contenu
        System.out.println("ProcedurePaa Id: " + procedurePaa.getId());
        System.out.println("ProcedurePaa Origine: " + procedurePaa.getOrigin());
        System.out.println("ProcedurePaa Destinataire: " + procedurePaa.getDestinataire());
        System.out.println("ProcedurePaa Description: " + procedurePaa.getDescription());
        System.out.println("ProcedurePaa DeadlineEstime: " + procedurePaa.getDeadlineEstime());

        // Load the JRXML file
        JasperReport jasperReport = JasperCompileManager.compileReport(
                "C:\\Users\\lapto\\Desktop\\stage_project_old\\passationBack-main\\passationBack-main\\authentification\\src\\main\\resources\\demandeIP.jrxml");

        // Create a JRBeanCollectionDataSource
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(List.of(procedurePaa));

        // Set parameters (if any)
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("ProcedureId", procedurePaa.getId());
        parameters.put("origine", procedurePaa.getOrigin());
        parameters.put("destinataire", procedurePaa.getDestinataire());
        parameters.put("description", procedurePaa.getDescription());
        parameters.put("sourceFinanciere", procedurePaa.getSourceFinanciere());
        parameters.put("deadlineEstime", Date.valueOf(procedurePaa.getDeadlineEstime())); // Convertir LocalDate en
                                                                                          // java.sql.Date
        parameters.put("id", procedurePaa.getPaa().getId());

        parameters.put("montant", procedurePaa.getMontant());

        // Fill the report
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        // Export the report to a byte array
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }

}
