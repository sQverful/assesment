package dorosh.elemental.assessment.handler;

import dorosh.elemental.assessment.exception.ErrorsException;
import dorosh.elemental.assessment.openapi.model.Error;
import dorosh.elemental.assessment.openapi.model.Errors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(value = {ErrorsException.class})
    public ResponseEntity<Errors> validationException(ErrorsException errorsException) {
        return ResponseEntity.badRequest().body(new Errors().errors(errorsException.getErrors()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> catchAllException(Exception exception) {
        return new ResponseEntity<>(new Error().detailMessage("An unexpected error occurred: " + exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
