package heath.med.NeoHealthCare.consulta;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CancelamentoConsulta {

  @NotNull
  private Long idConsulta;

  @NotNull
  Cancelamento motivoCancelamento;

}
