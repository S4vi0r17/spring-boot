package med.voll.api.infra.errores;

import jakarta.persistence.EntityNotFoundException;
import med.voll.api.domain.ValidacionException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorDeErrores {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> tratarError404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> tratarError400(MethodArgumentNotValidException e) {
        var errores = e.getFieldErrors().stream()
                .map(ErrorValidationDto::new)
                .toList();
        return ResponseEntity.badRequest().body(errores);
    }

    @ExceptionHandler(ValidacionException.class)
    public ResponseEntity<?> tratarErrorDeValidacion(ValidacionException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    private record ErrorValidationDto(String field, String message) {
        ErrorValidationDto(org.springframework.validation.FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
