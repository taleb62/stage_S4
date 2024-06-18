package org.sid.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class SubmissionService {

    private final String rootDirectory = "uploads"; // Dossier racine des soumissions

    public void saveFile(MultipartFile file, String subdirectory) throws IOException {
        Path directoryPath = Paths.get(rootDirectory, subdirectory);

        // Crée le dossier s'il n'existe pas
        if (!Files.exists(directoryPath)) {
            Files.createDirectories(directoryPath);
        }

        // Sauvegarde le fichier
        Path filePath = directoryPath.resolve(file.getOriginalFilename());
        Files.write(filePath, file.getBytes());
    }
}
