package dorosh.elemental.assessment.validator;

import dorosh.elemental.assessment.openapi.model.Error;
import lombok.NonNull;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

@Component
public class InputFileValidator {

    private static final String UUID_PATTERN = "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}";
    private static final String ID_PATTERN = "[a-zA-Z0-9]+";
    private static final String NAME_PATTERN = "[a-zA-Z ]+";
    private static final String LIKES_PATTERN = "[a-zA-Z ]+";
    private static final String TRANSPORT_PATTERN = "[a-zA-Z ]+";
    private static final String SPEED_PATTERN = "\\d+(\\.\\d{1})?";

    private static final String CONTENT_PATTERN = String.join("\\|",
            "(" + UUID_PATTERN + ")",
            "(" + ID_PATTERN + ")",
            "(" + NAME_PATTERN + ")",
            "(" + LIKES_PATTERN + ")",
            "(" + TRANSPORT_PATTERN + ")",
            "(" + SPEED_PATTERN + ")",
            "(" + SPEED_PATTERN + ")"
    ) + "(\\r?\\n)?";

    private static final Pattern PATTERN = Pattern.compile(CONTENT_PATTERN);

    public void validate(@NonNull Resource inputFile, @NonNull List<Error> errors) {
        String content;
        try {
            content = new String(inputFile.getContentAsByteArray());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String[] rows = content.split("\\r?\\n");
        for (String row : rows) {
            if (!PATTERN.matcher(row).matches())
                errors.add(new Error().detailMessage("File content does not follow the specified pattern."));
        }
    }
}
