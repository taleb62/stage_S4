package org.sid.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.apache.commons.io.FilenameUtils;
import org.sid.dao.InputBudgetaireRepository;
import org.sid.dao.planRepository;
import org.sid.entites.AppUser;
import org.sid.entites.InputBudgetaire;
import org.sid.entites.plan_anuell_achat;
import org.sid.entites.DTO.PaaFormProcedure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.util.Iterator;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.apache.poi.ss.usermodel.*;

@Service
@Transactional
public class PaaServiceImpl implements PaaService {
    @Autowired
    private planRepository repo;

    @Autowired
    private InputBudgetaireRepository input;

    @Override
    public List<plan_anuell_achat> getAllPaa() {
        return repo.findAll();
        // return repo.getAllPaas();
    }

    public Optional<plan_anuell_achat> getPaa(Integer id) {
        return repo.findById(id);
    }

    public List<plan_anuell_achat> getPlansByEtablissementId(Integer etablissementId) {
        return repo.findByEtablissementId(etablissementId);
    }

    public ArrayNode getListPaa() {

        List<Map<String, Object>> paas = repo.getPaas();
        ArrayNode res = new ObjectMapper().createArrayNode();

        paas.forEach(paa -> {
            res.add(new ObjectMapper().createObjectNode()
                    .put("id", paa.get("id").toString())
                    .put("date_cration_procedure", paa.get("date_cration_procedure").toString())
                    .put("date_previ_attribution", paa.get("date_previ_attribution").toString())
                    .put("date_previ_lancement", paa.get("date_previ_lancement").toString())
                    .put("destinataire", paa.get("destinataire").toString()));
        });
        System.out.println(res);
        return res;
    }

    @Override
    public plan_anuell_achat updatePaa(Integer id, String origine, String destinataire) {
        plan_anuell_achat paa = repo.getPaa(id);
        if (paa == null)
            throw new RuntimeException("le PAA non trouvé !");
        repo.updatePaa(origine, destinataire, !paa.isEnprocedure(), id);
        plan_anuell_achat paa1 = repo.getPaa(id);
        return paa1;
    }
    public plan_anuell_achat modifierPaa(Integer id,PaaFormProcedure Paa ) {
        plan_anuell_achat paa = repo.getPaa(id);
        if (paa == null)
            throw new RuntimeException("le PAA non trouvé !");
        paa.setObjetDepense(Paa.getObjetDepense());
        paa.setInpuBudgetaire(Paa.getInpuBudgetaire());
        paa.setMntEstimatif(Paa.getMntEstimatif());
        if (paa.getMntEstimatif()>paa.getMontantRestant()) {
            paa.setMontantRestant(Paa.getMntEstimatif() - paa.getMontantRestant());
        } else {
            paa.setMontantRestant(Paa.getMntEstimatif());
            
        }
        paa.setDatePreviAttribution(Paa.getDatePreviAttribution());
        paa.setDatePreviLancement(Paa.getDatePreviLancement());
        repo.save(paa);
        return paa;
    }

    @Override
    public plan_anuell_achat addPaa(PaaFormProcedure data) {

        InputBudgetaire inputBudgetaire = input.findByBudgetNumber(data.getInpuBudgetaire());

        if (inputBudgetaire != null) {
            
            plan_anuell_achat newPaa = new plan_anuell_achat();
            newPaa.setObjetDepense(data.getObjetDepense());
            newPaa.setInpuBudgetaire(data.getInpuBudgetaire());
            newPaa.setFkidTypeMarche(inputBudgetaire.getTypeMarcher().getId());
            newPaa.setFkidModPassation(inputBudgetaire.getTypeSelection().getId());
            newPaa.setEtablissement(inputBudgetaire.getEtablissement());
            newPaa.setMntEstimatif(data.getMntEstimatif());
            newPaa.setDatePreviLancement(data.getDatePreviLancement());
            newPaa.setDatePreviAttribution(data.getDatePreviAttribution());
            newPaa.setDateCrationProcedure(data.getDateCrationProcedure());
            newPaa.setMontantRestant(data.getMntEstimatif());
            return repo.save(newPaa);
        }
        return null;

    }

    public plan_anuell_achat validatePlanAnuellAchat(Integer id) {
        // Fetch the plan_anuell_achat by id
        plan_anuell_achat paa = repo.getPaa(id);

        // Set the validation status to true
        paa.setIsvalidated(true);

        // Save the updated plan_anuell_achat back to the database
        return repo.save(paa);
    }

    public ResponseEntity<String> saveExcelData(MultipartFile file) throws IOException {
        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rows = sheet.iterator();

        // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd"); //
        // Adapt the pattern to your date
        // format

        while (rows.hasNext()) {
            Row currentRow = rows.next();
            if (currentRow.getRowNum() == 0) { // Skip header row
                continue;
            }

            // Check if the row is empty and break the loop if it is
            if (isRowEmpty(currentRow)) {
                break;
            }

            plan_anuell_achat paa = new plan_anuell_achat();

            InputBudgetaire inputBudgetaire = input.findByBudgetNumber(getStringCellValue(currentRow.getCell(1)));

            if (inputBudgetaire != null) {

                paa.setObjetDepense(getStringCellValue(currentRow.getCell(0)));
                paa.setInpuBudgetaire(getStringCellValue(currentRow.getCell(1)));
                paa.setMntEstimatif(getNumericCellValue(currentRow.getCell(2)));
                paa.setMontantRestant(getNumericCellValue(currentRow.getCell(2)));

                LocalDate datePreviLancement = getLocalDateFromString(currentRow.getCell(3));
                LocalDate datePreviAttribution = getLocalDateFromString(currentRow.getCell(4));

                paa.setDatePreviLancement(datePreviLancement);
                paa.setDatePreviAttribution(datePreviAttribution);
                paa.setEtablissement(inputBudgetaire.getEtablissement());
                paa.setFkidModPassation(inputBudgetaire.getTypeSelection().getId());
                paa.setFkidTypeMarche(inputBudgetaire.getTypeMarcher().getId());

                // Ajoutez d'autres colonnes selon votre table
                repo.save(paa);
            } else {
                return new ResponseEntity<String>("l'input budgetaire est invalid",
                        HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }

        workbook.close();
        return new ResponseEntity<String>("PAA ajouté ",
                HttpStatus.CREATED);
    }

    private boolean isRowEmpty(Row row) {
        for (int cellNum = 0; cellNum < row.getLastCellNum(); cellNum++) {
            Cell cell = row.getCell(cellNum);
            if (cell != null && cell.getCellType() != CellType.BLANK) {
                return false;
            }
        }
        return true;
    }

    private String getStringCellValue(Cell cell) {
        if (cell == null) {
            return "";
        }
        cell.setCellType(CellType.STRING);
        return cell.getStringCellValue();
    }

    private double getNumericCellValue(Cell cell) {
        if (cell == null) {
            return 0;
        }
        if (cell.getCellType() == CellType.STRING) {
            return Double.parseDouble(cell.getStringCellValue());
        }
        return cell.getNumericCellValue();
    }

    public LocalDate getLocalDateFromString(Cell cell) {
        if (cell == null || cell.getCellType() == CellType.BLANK) {
            return null; // Handle empty cells
        }

        try {
            if (cell.getCellType() == CellType.NUMERIC && DateUtil.isCellDateFormatted(cell)) {
                // If the cell is a date in numeric format
                return cell.getDateCellValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            } else if (cell.getCellType() == CellType.STRING) {
                // Attempt to parse the date from a string
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Adjust format as needed
                return LocalDate.parse(cell.getStringCellValue(), formatter);
            } else {
                // Handle other cell types if needed
                System.err.println("Unsupported cell type for date parsing.");
                return null;
            }
        } catch (DateTimeParseException | IllegalStateException e) {
            // Handle date parsing errors
            System.err.println("Error parsing date: " + e.getMessage());
            return null;
        }
    }

    

    @Override
    public void deletePaa(Integer id) {
        repo.deleteById(id);
    }

}
