package heath.med.NeoHealthCare.dto;

import heath.med.NeoHealthCare.consulta.CancelamentoConsulta;
import heath.med.NeoHealthCare.repository.ConsultaRepository;
import heath.med.NeoHealthCare.domain.medico.Especialidade;
import heath.med.NeoHealthCare.exception.GenericException;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AgendamentoConsultaDTO {

  @Autowired
  private ConsultaRepository consultaRepository;

  private Long idMedico;

  @NotNull
  private Long idPaciente;

  @NotNull
  @Future
  private LocalDateTime data;

  private Especialidade especialidade;

  public void cancelar(CancelamentoConsulta dados){
    if(!consultaRepository.existsById(dados.getIdConsulta())){
      throw new GenericException("Consulta não encontrada");
    }
    var consulta = consultaRepository.getReferenceById(dados.getIdConsulta());
    consulta.cancelar(dados.getMotivoCancelamento());
  }

}
