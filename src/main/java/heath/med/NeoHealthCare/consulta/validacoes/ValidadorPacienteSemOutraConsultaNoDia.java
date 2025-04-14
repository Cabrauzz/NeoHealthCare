package heath.med.NeoHealthCare.consulta.validacoes;

import heath.med.NeoHealthCare.dto.AgendamentoConsultaDTO;
import heath.med.NeoHealthCare.exception.GenericException;
import heath.med.NeoHealthCare.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteSemOutraConsultaNoDia implements ValidarAgendamento{

  @Autowired
  private ConsultaRepository consultaRepository;

  @Override
  public void validar(AgendamentoConsultaDTO agendamentoConsultaDTO){
    var f1rstHour = agendamentoConsultaDTO.getData().withHour(7);
    var lastHour = agendamentoConsultaDTO.getData().withHour(18);
    var consulta = consultaRepository.existsByPacienteIdAndDataBetween(agendamentoConsultaDTO.getIdPaciente(),
        f1rstHour, lastHour);

    if(consulta){
      throw new GenericException("Paciente já possui uma consulta agendada para o dia");
    }
  }
}
