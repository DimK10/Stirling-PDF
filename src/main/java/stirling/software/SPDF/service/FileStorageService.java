package stirling.software.SPDF.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileStorageService {

    private static final String STORAGE_PATH_TEST =
            "D:\\app\\data"; // Directory inside the container
    private static final String STORAGE_PATH = "/app/data"; // Directory inside the container

    public String saveFile(MultipartFile file) throws IOException {
        File directory = new File(STORAGE_PATH_TEST);
        if (!directory.exists()) {
            directory.mkdirs(); // Create the directory if it doesn't exist
        }

        UUID uuid = UUID.randomUUID();

        String newFilename = uuid.toString();

        File savedFile = new File(directory, newFilename);
        try (FileOutputStream fos = new FileOutputStream(savedFile)) {
            fos.write(file.getBytes());
        }
        return newFilename;
    }
}
