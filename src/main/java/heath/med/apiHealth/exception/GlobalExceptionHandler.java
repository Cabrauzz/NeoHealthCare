package heath.med.apiHealth.exception;

import heath.med.apiHealth.dto.ErroResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity handleGenericException(EntityNotFoundException ex, WebRequest request) {
    return ResponseEntity.notFound().build();
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity error500(Exception ex, WebRequest request) {
    return ResponseEntity.internalServerError().body(" Usuário inexistente ou senha inválida");
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity error400(MethodArgumentNotValidException ex, WebRequest request) {
    var error = ex.getFieldErrors();
    return ResponseEntity.badRequest().body(error.stream().map(ErroResponse::new).toList());
  }
}