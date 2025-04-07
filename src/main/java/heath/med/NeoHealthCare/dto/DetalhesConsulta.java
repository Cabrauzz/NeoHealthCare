package heath.med.NeoHealthCare.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DetalhesConsulta {

  private Long id;

  private Long idMedico;

  private Long idPaciente;

  private LocalDateTime data;

}
