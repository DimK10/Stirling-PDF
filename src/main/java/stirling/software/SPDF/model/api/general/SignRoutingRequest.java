package stirling.software.SPDF.model.api.general;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode
public class SignRoutingRequest {

    private List<String> emails;

    private byte[] pdfFile;
}
