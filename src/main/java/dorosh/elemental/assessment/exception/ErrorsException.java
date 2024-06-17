package dorosh.elemental.assessment.exception;

import dorosh.elemental.assessment.openapi.model.Error;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class ErrorsException extends RuntimeException {
    private final List<Error> errors;
}

