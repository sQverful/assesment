package dorosh.elemental.assessment.api;

import dorosh.elemental.assessment.openapi.api.FilesApi;
import dorosh.elemental.assessment.openapi.model.EntryFileOutcome;

import dorosh.elemental.assessment.service.FileProcessingService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class FilesApiImpl implements FilesApi {

    private final FileProcessingService fileProcessingService;

    @Override
    public ResponseEntity<List<EntryFileOutcome>> processFile(@NonNull Resource body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setContentDispositionFormData("attachment", "OutcomeFile.json");

        return ResponseEntity.ok()
                .headers(headers)
                .body(fileProcessingService.convertToJson(body));
    }
}
