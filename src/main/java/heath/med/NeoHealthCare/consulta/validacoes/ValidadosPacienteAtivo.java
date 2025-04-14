package heath.med.NeoHealthCare.consulta.validacoes;

import heath.med.NeoHealthCare.dto.AgendamentoConsultaDTO;
import heath.med.NeoHealthCare.exception.GenericException;
import heath.med.NeoHealthCare.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadosPacienteAtivo implements ValidarAgendamento {

  @Autowired
  private PacienteRepository pacienteRepository;

  @Override
  public void validar(AgendamentoConsultaDTO agendamentoConsultaDTO) {

    if (agendamentoConsultaDTO.getIdPaciente() == null) {
      return;
    }

    var pacienteAtivo = pacienteRepository.findAtivoById(agendamentoConsultaDTO.getIdPaciente());

    if (!pacienteAtivo) {
      throw new GenericException("Paciente não está ativo");
    }
  }
}