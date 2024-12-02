package stirling.software.SPDF.service;

import java.io.IOException;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.mail.MessagingException;
import jakarta.servlet.ServletContext;

@Service
public class SignRoutingService {

    @Autowired private EmailService emailService;

    @Autowired private FileStorageService fileStorageService;

    @Autowired private ServletContext servletContext;

    private String constructUrlFromFile(MultipartFile file) throws IOException {
        // save file with ato generated uuid
        String filename = fileStorageService.saveFile(file);

        // construct the url to access file
        String test = servletContext.getContextPath() + "?filename=" + filename;
        return servletContext.getContextPath() + "?filename=" + filename;
    }

    private void SendEmails(Set<String> emails, String url) throws MessagingException {
        for (String email : emails) {
            emailService.sendSignMail(email, "Strirling PDF Invitation to Sign", url);
        }
    }

    public String handleSignRouting(Set<String> emails, MultipartFile file)
            throws IOException, MessagingException {
        String url = constructUrlFromFile(file);

        SendEmails(emails, url);

        return url;
    }
}
