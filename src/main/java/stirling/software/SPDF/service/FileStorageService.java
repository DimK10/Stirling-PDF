package stirling.software.SPDF.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class FileStorageService {

    private static final String STORAGE_PATH_TEST = "D:\\app\\data"; // Directory inside the container
    private static final String STORAGE_PATH = "/app/data"; // Directory inside the container

    public String saveFile(MultipartFile file) throws IOException {
        File directory = new File(STORAGE_PATH_TEST);
        if (!directory.exists()) {
            directory.mkdirs(); // Create the directory if it doesn't exist
        }

        File savedFile = new File(directory, file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(savedFile)) {
            fos.write(file.getBytes());
        }
        return "File saved at: " + savedFile.getAbsolutePath();
    }
}