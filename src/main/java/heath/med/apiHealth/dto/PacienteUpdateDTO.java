package heath.med.apiHealth.dto;

import heath.med.apiHealth.domain.endereco.Endereco;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class PacienteUpdateDTO {

  @NotNull
  private Long id;

  private String nome;

  private String telefone;

  private Endereco endereco;
}
