package org.sid.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.apache.commons.io.FilenameUtils;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.sid.dao.FileDBRepository;
import org.sid.dao.GedStorageRepository;
import org.sid.entites.FileDB;
import org.sid.entites.GedStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Stream;
@Service
public class FileStorageService {
    @Autowired
    private FileDBRepository fileDBRepository;

    @Autowired
    private GedStorageRepository storageRepository;

    @Value("${upload.path}")
    private String uploadPath;

    public FileDB store(MultipartFile file, Integer idPaa,int fkIdTbl,Integer fkIduser) throws IOException {

        String fileExt =
                FilenameUtils.getExtension(file.getOriginalFilename()).toLowerCase();
        GedStorage storage = storageRepository.getPath();
//        String subFolder =
//               new SimpleDateFormat("yyyy").format(new Date()) + File.separator + new SimpleDateFormat("MM").format(new Date()) + File.separator;
        String fileNameOnDisc =
                "doc_" + new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss_SSS").format(new Date()) + getRandom() + "." + fileExt;
        String subFolder =
                new SimpleDateFormat("yyyy").format(new Date()) + File.separator + new SimpleDateFormat("MM").format(new Date()) + File.separator;

        File pathFileAnnee = new File("C:\\uploads\\"+new SimpleDateFormat("yyyy").format(new Date())+"\\"+new SimpleDateFormat("MM").format(new Date()));
        File pathFile = new File("C:\\uploads");
        //System.out.println(C\\uploads\\);
        if(!Files.exists(Paths.get(String.valueOf(pathFile)))){
            pathFile.mkdir();
            
            pathFileAnnee.mkdirs();
        }else{
            pathFileAnnee.mkdirs();
        }

        Path root = Paths.get(String.valueOf(pathFileAnnee));
        Path resolve = root.resolve(fileNameOnDisc);
        System.out.println(resolve);
//        if (resolve.toFile()
//                .exists()) {
//            throw new FileUploadException("File already exists: " + file.getOriginalFilename());
//        }
        Files.copy(file.getInputStream(), resolve);
//
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        FileDB fileDB = new FileDB(fileName, fileNameOnDisc, subFolder,idPaa,fkIdTbl,fkIduser);
//
        return fileDBRepository.save(fileDB);
        //return "************************* mamadou";
    }
    private String getRandom() {
        Random rand = new Random(); //instance of random class
        int upperbound = 100;
        //generate random values from 0-24
        int int_random = rand.nextInt(upperbound);
        return String.valueOf(int_random);
    }


//    public Resource load(String filename) {
//        try {
//            Path file = Paths.get(uploadPath)
//                    .resolve(filename);
//            Resource resource = new UrlResource(file.toUri());
//
//            if (resource.exists() || resource.isReadable()) {
//                return resource;
//            } else {
//                throw new RuntimeException("Could not read the file!");
//            }
//        } catch (MalformedURLException e) {
//            throw new RuntimeException("Error: " + e.getMessage());
//        }
//    }
//    public FileDB getFile(String id) {
//        return fileDBRepository.findById(id).get();
//    }

    public Stream<FileDB> getAllFiles(Integer idPaa,Integer fkIduser) {
        return fileDBRepository.getListdocument(idPaa,fkIduser);
    }

    public ArrayNode getListDocs(Integer idPaa,Integer fkIduser,int fkIdTbl) {

        List<Map<String, Object>> sysGedFiles =
                fileDBRepository.findAllByIdElemAndFkIdTblAndCodeAndFileName(idPaa,fkIduser,fkIdTbl);
        ArrayNode res = new ObjectMapper().createArrayNode();

        sysGedFiles.forEach(sysGedFile -> {

                    String extFile = FilenameUtils.getExtension(sysGedFile.get("file_name_on_disc").toString());


                    res.add(new ObjectMapper().createObjectNode()
                            .put("id", sysGedFile.get("id").toString())
                            .put("name", sysGedFile.get("name").toString())
                            .put("fileUrl", uploadPath+"\\"+sysGedFile.get("file_sub_folder").toString()+sysGedFile.get("file_name_on_disc").toString())
                    );
                }
        );
       System.out.println(res);
        return res;
    }

    public Resource download(String filename,String fileSubFolder) {
        try {
            String root = uploadPath+"\\"+fileSubFolder;
            System.out.println(root);
            Path file = Paths.get(root)
                    .resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }


    public List<FileDB> getdocumentPaa(Integer idPaa,Integer fkIdTbl){
        return fileDBRepository.getdocumentPaa(idPaa,fkIdTbl);
    }
}
