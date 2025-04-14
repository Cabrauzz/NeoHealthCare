package heath.med.NeoHealthCare.consulta.validacoes;

import heath.med.NeoHealthCare.dto.AgendamentoConsultaDTO;
import heath.med.NeoHealthCare.exception.GenericException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
@Component
public class ValidadorHorarioClinica implements ValidarAgendamento{

  @Override
  public void validar(AgendamentoConsultaDTO agendamentoConsultaDTO) {
    if (agendamentoConsultaDTO.getData().getDayOfWeek().equals(DayOfWeek.SUNDAY) ||
        agendamentoConsultaDTO.getData().getHour() < 7 ||
        agendamentoConsultaDTO.getData().getHour() > 18) {
      throw new GenericException("Consulta fora do horário de funcionamento da clínica");
    }
  }
}