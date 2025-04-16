package heath.med.NeoHealthCare.consulta.validacoes;

import heath.med.NeoHealthCare.dto.AgendamentoConsultaDTO;
import heath.med.NeoHealthCare.exception.GenericException;

import java.time.Duration;
import java.time.LocalDateTime;

public class ValidadorHoraAntecedencia implements ValidarAgendamento{

  public void validar(AgendamentoConsultaDTO agendamentoConsultaDTO) {
    var now = LocalDateTime.now();
    var minutos = Duration.between(now, agendamentoConsultaDTO.getData()).toMinutes();

    if (minutos < 30 ) {
      throw new GenericException("Agendamento só pode ser feito com 30 minutos de antecedência");
    }
  }
}
