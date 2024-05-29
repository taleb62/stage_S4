package org.sid.web;

import net.sf.jasperreports.engine.JRException;
import org.sid.services.ServiceReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/rest/Report")
public class ReportController {

    @Autowired
    ServiceReport report;

    @CrossOrigin(origins = "*")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity generateReport(@PathVariable Integer id, @RequestParam("reportName") String reportName) throws IOException, JRException {
        return report.exportReportLettre(id,reportName);
    }
}
