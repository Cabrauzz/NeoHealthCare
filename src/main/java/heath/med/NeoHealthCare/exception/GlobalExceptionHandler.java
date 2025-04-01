package heath.med.NeoHealthCare.exception;

import heath.med.NeoHealthCare.dto.ErroResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity handleGenericException(EntityNotFoundException ex, WebRequest request) {
    return ResponseEntity.notFound().build();
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity error400(MethodArgumentNotValidException ex, WebRequest request) {
    var error = ex.getFieldErrors();
    return ResponseEntity.badRequest().body(error.stream().map(ErroResponse::new).toList());
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity tratarErro400(HttpMessageNotReadableException ex) {
    return ResponseEntity.badRequest().body(ex.getMessage());
  }

  @ExceptionHandler(BadCredentialsException.class)
  public ResponseEntity tratarErroBadCredentials() {
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas");
  }

  @ExceptionHandler(AccessDeniedException.class)
  public ResponseEntity tratarErroAcessoNegado() {
    return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acesso negado");
  }
}