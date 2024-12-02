package stirling.software.SPDF.model.api.general;

import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class SignRoutingRequest {

    @NotEmpty private Set<String> emails;

    @NotNull private MultipartFile pdfFile;
}
