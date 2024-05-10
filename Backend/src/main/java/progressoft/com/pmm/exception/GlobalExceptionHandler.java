package progressoft.com.pmm.exception;

import com.example.model.ServerResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ParticipantAlreadyExistsException.class})
    public ResponseEntity<ServerResponse> handleParticipantAlreadyExistsException(ParticipantAlreadyExistsException ex) {
        return ResponseEntity.badRequest().body(buildResponse(ex.getMessage()));
    }

    @ExceptionHandler({ParticipantNotFoundException.class})
    public ResponseEntity<ServerResponse> handleParticipantNotFoundException(ParticipantNotFoundException ex) {
        return ResponseEntity.badRequest().body(buildResponse(ex.getMessage()));
    }

    @ExceptionHandler({InvalidImageException.class})
    public ResponseEntity<ServerResponse> handleInvalidImageException(InvalidImageException ex) {
        return ResponseEntity.badRequest().body(buildResponse(ex.getMessage()));
    }

    @ExceptionHandler({InvalidSettlementBankValue.class})
    public ResponseEntity<ServerResponse> handleInvalidSettlementBankValue(InvalidSettlementBankValue ex) {
        return ResponseEntity.badRequest().body(buildResponse(ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ServerResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        ServerResponse response = buildResponse(getErrorDescription(result.getFieldErrors()));
        return ResponseEntity.badRequest().body(response);
    }

    private ServerResponse buildResponse(String errorMessage) {
        return ServerResponse.builder()
                .message(errorMessage)
                .statusCode(BAD_REQUEST.value())
                .build();
    }

    private String getErrorDescription(List<FieldError> fieldErrors) {
        List<String> errors = new ArrayList<>();
        for (FieldError fieldError : fieldErrors) {
            errors.add(fieldError.getDefaultMessage());
        }
        return String.join(", ", errors);
    }
}