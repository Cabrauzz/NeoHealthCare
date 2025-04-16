package heath.med.NeoHealthCare.dto;

import heath.med.NeoHealthCare.consulta.Consulta;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetalhesConsulta {

  private Long id;

  private Long idMedico;

  private Long idPaciente;

  private LocalDateTime data;

  public DetalhesConsulta(Consulta consulta) {
    this(consulta.getId(), consulta.getMedico().getId(), consulta.getPaciente().getId(), consulta.getData());
  }
}
