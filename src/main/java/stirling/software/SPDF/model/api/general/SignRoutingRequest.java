package stirling.software.SPDF.model.api.general;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@Data
@EqualsAndHashCode
public class SignRoutingRequest {

    @NotEmpty
    private Set<String> emails;

    @NotNull
    private MultipartFile pdfFile;
}
