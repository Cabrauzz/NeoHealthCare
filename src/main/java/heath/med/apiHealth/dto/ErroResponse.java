package heath.med.apiHealth.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.FieldError;

@Getter
@Setter
@NoArgsConstructor
public class ErroResponse {

  private String campo;
  private String mensagem;

  public ErroResponse(String campo, String mensagem) {
    this.campo = campo;
    this.mensagem = mensagem;
  }

  public ErroResponse(FieldError fieldError) {
    this.campo = fieldError.getField();
    this.mensagem = fieldError.getDefaultMessage();
  }

}
