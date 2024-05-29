package org.sid.web;

import com.fasterxml.jackson.databind.node.ArrayNode;
import org.sid.entites.FileDB;
import org.sid.message.ResponseFile;
import org.sid.message.ResponseMessage;
import org.sid.services.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
@RestController
//@CrossOrigin("http://localhost:8083")
@RequestMapping("/api/rest/uploadFiles")
public class FileController {
    @Autowired
    private FileStorageService storageService;


    @CrossOrigin(origins = "*")
    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file,
                                                      @RequestParam("idElm") Integer idPaa,
                                                      @RequestParam("fkIdTbl") Integer fkIdTbl,
                                                      @RequestParam("fkIduser") int fkIduser) {
        String message = "";
        try {
            storageService.store(file,idPaa,fkIdTbl,fkIduser);

            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }
    @CrossOrigin(origins = "*")
    @GetMapping("/files")
    public ResponseEntity<List<ResponseFile>> getListFiles(@RequestParam("idElm") Integer idPaa, @RequestParam("fkIduser") Integer fkIduser) {
        List<ResponseFile> files = storageService.getAllFiles(idPaa,fkIduser).map(dbFile -> {
            System.out.println(dbFile);
//            String fileDownloadUri = ServletUriComponentsBuilder
//                    .fromCurrentContextPath()
//                    .path("/files/")
//                    .path(dbFile.getId(),dbFile.getFkIduser())
//                    .toUriString();

            return new ResponseFile(
                        dbFile.getName(),
                    dbFile.getFileSubFolder()+"\\"+dbFile.getFileNameOnDisc());

        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @GetMapping("/listDocs")
    public ArrayNode getListDocs(@RequestParam("idElm") Integer idPaa, @RequestParam("fkIduser") Integer fkIduser,@RequestParam("fkIduser") int fkIdTbl) {
        return storageService.getListDocs(idPaa,fkIduser,fkIdTbl);
    }
//    @GetMapping("/files/{id}")
//    public ResponseEntity<byte[]> getFile(@PathVariable String id) {
//        FileDB fileDB = storageService.getFile(id);
//
//        return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
//                .body(fileDB.getData());
//    }

    @GetMapping("{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename,@RequestParam("fileSubFolder") String fileSubFolder) throws IOException {
        Resource file = storageService.download(filename,fileSubFolder);
        Path path = file.getFile()
                .toPath();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, Files.probeContentType(path))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }

    @GetMapping("/listDocsPaa")
    public List<FileDB> getdocumentPaa(@RequestParam("idElm") Integer idPaa, @RequestParam("fkIdTbl") Integer fkIdTbl) {
        return storageService.getdocumentPaa(idPaa,fkIdTbl);
    }


}
