package stirling.software.SPDF.controller.api;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import stirling.software.SPDF.model.api.general.SignRoutingRequest;
import stirling.software.SPDF.service.SignRoutingService;

@RestController
@RequestMapping("/api/v1/general")
@Tag(name = "General", description = "General APIs")
public class SignRoutingController {

    private static final Logger logger = LoggerFactory.getLogger(SignRoutingController.class);

    @Autowired private SignRoutingService signRoutingService;

    @PostMapping(value = "/sign-routing", consumes = "multipart/form-data")
    @Operation(
            summary = "Save pdf file locally for sign routing",
            description =
                    "This operation takes an input PDF file and the emails of the users that need to sign it. It stores the file for signing from those users")
    public ResponseEntity handleSignRouting(
            @Valid @RequestBody SignRoutingRequest signRoutingRequest)
            throws MessagingException, IOException {

        try {
            String url =
                    signRoutingService.handleSignRouting(
                            signRoutingRequest.getEmails(), signRoutingRequest.getPdfFile());

            Map<String, String> response = new HashMap<>();
            response.put("url", url);

            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
