package heath.med.NeoHealthCare.consulta.validacoes;

import heath.med.NeoHealthCare.dto.AgendamentoConsultaDTO;
import heath.med.NeoHealthCare.exception.GenericException;
import heath.med.NeoHealthCare.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoAtivo implements ValidarAgendamento{

  @Autowired
  private MedicoRepository medicoRepository;

  @Override
  public void validar(AgendamentoConsultaDTO agendamentoConsultaDTO) {

    if (agendamentoConsultaDTO.getIdMedico() == null) {
      return;
    }

    var medicoAtivo = medicoRepository.findAtivoById(agendamentoConsultaDTO.getIdMedico());

    if (!medicoAtivo) {
      throw new GenericException("Consulta não pode ser agendada com médico inativo");
    }
  }
}