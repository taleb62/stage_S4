package org.sid.services;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.sid.dao.planRepository;
import org.sid.entites.DTO.PaaProcedure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.*;

@Service
public class ServiceReport {

    @Autowired
    private planRepository repo ;

    @Value("${spring.datasource.username}")
    private String root;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.url}")
    private String connection_url;

    @Value("${reports.path}")
    private String reportsPath;

    public ResponseEntity exportReport(Integer id) throws FileNotFoundException, JRException , IOException {
//        String path = "C:\\Report";
        //File pdfFile = File.createTempFile("my-invoice", ".pdf");
        //List<T_donnee_personnel_admin> employees = repo.findAll();
        List<PaaProcedure> report = repo.getPaaReport(id);

        System.out.println(report);
        try {

            //load file and compile it
            File file = ResourceUtils.getFile("classpath:initiation-procedure.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(report);
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("createdBy", "Java Techie");
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
//        //if (reportFormat.equalsIgnoreCase("html")) {
//           // JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "\\bulletins.html");
//        //}
//       // if (reportFormat.equalsIgnoreCase("pdf")) {
            // JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\initiation.pdf");
            byte data[] = JasperExportManager.exportReportToPdf(jasperPrint);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "inline; filename=initiation.pdf");

            return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
        }catch (Exception e) {
            return ResponseEntity.status(500).body("Erreur: " + e.getMessage());
        }
    }

    public ResponseEntity exportReportLettre(Integer id,String reportName) throws FileNotFoundException, JRException , IOException {

        try {

            //load file and compile it
            File file = ResourceUtils.getFile(reportsPath + File.separator + reportName + ".jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
            //JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(report);
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("id", id);
            Connection conn = DriverManager.getConnection(connection_url, root, password);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,conn);
            byte data[] = JasperExportManager.exportReportToPdf(jasperPrint);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "inline; filename="+reportName+".pdf");

            return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
        }catch (Exception e) {
            return ResponseEntity.status(500).body("Erreur: " + e.getMessage());
        }
    }
}
