package heath.med.NeoHealthCare.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AgendamentoConsultaDTO {

  private Long idMedico;

  @NotNull
  private Long idPaciente;

  @NotNull
  @Future
  private LocalDateTime data;

}
