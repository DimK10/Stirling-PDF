package stirling.software.SPDF.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class SignRoutingService {


    @Autowired
    private EmailService emailService;

    @Autowired
    private FileStorageService fileStorageService;

    public void SendEmails(Set<String> emails) {
        for (String email : emails) {
            emailService.sendSimpleMessage(email, "Strirling PDF Invitation to Sign", "test");
        }
    }

}
