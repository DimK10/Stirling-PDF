package stirling.software.SPDF.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MultipleSignService {

    @Value("classpath:pdfs/*")
    private Resource[] resources;

    public List<File> PdfsForSign() {
        return Arrays.stream(resources)
                .map(
                        r -> {
                            try {
                                return r.getFile();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        })
                .collect(Collectors.toList());
    }
}
