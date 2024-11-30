package stirling.software.SPDF.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import stirling.software.SPDF.model.api.general.SignRoutingRequest;

@RestController
@RequestMapping("/api/v1/general")
@Tag(name = "General", description = "General APIs")
public class SignRoutingController {

    private static final Logger logger = LoggerFactory.getLogger(SignRoutingController.class);

    @PostMapping(value = "/scale-pages", consumes = "multipart/form-data")
    @Operation(
            summary = "Change the size of a PDF page/document",
            description =
                    "This operation takes an input PDF file and the size to scale the pages to in the output PDF file. Input:PDF Output:PDF Type:SISO")
    public ResponseEntity handleSignRounting(@ModelAttribute SignRoutingRequest signRoutingRequest) {
        // todo
        return ResponseEntity.ok().build();
    }

}
