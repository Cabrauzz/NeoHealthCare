package heath.med.NeoHealthCare.consulta.validacoes;

import heath.med.NeoHealthCare.dto.AgendamentoConsultaDTO;
import heath.med.NeoHealthCare.exception.GenericException;
import heath.med.NeoHealthCare.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidarMedicoComOutraConsulta implements ValidarAgendamento{

  @Autowired
  private ConsultaRepository consultaRepository;

  @Override
  public void validar(AgendamentoConsultaDTO agendamentoConsultaDTO) {
    var consulta = consultaRepository.existsByMedicoIdAndData(agendamentoConsultaDTO.getIdMedico(), agendamentoConsultaDTO.getData());

    if(consulta){
      throw new GenericException("Médico já possui uma consulta agendada para o dia");
    }
  }
}