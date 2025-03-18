package heath.med.apiHealth.domain.usuario;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DadosAutenticacao {

  @NotBlank(message = "Campo login Obrigatório")
  private String login;

  @NotBlank(message = "Campo senha Obrigatório")
  private String senha;
}
