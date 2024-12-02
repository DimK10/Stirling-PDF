package stirling.software.SPDF.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stirling.software.SPDF.model.api.general.SignRoutingRequest;

@RestController
@RequestMapping("/api/v1/general")
@Tag(name = "General", description = "General APIs")
public class SignRoutingController {

    private static final Logger logger = LoggerFactory.getLogger(SignRoutingController.class);

    @PostMapping(value = "/scale-pages", consumes = "multipart/form-data")
    @Operation(
            summary = "Save pdf file locally for sign routing",
            description =
                    "This operation takes an input PDF file and the emails of the users that need to sign it. It stores the file for signing from those users")
    public ResponseEntity handleSignRounting(@Valid @RequestBody SignRoutingRequest signRoutingRequest) {
        // todo
        return ResponseEntity.ok().build();
    }

}
