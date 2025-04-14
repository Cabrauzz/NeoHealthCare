package heath.med.NeoHealthCare.service;

import heath.med.NeoHealthCare.consulta.Cancelamento;
import heath.med.NeoHealthCare.consulta.Consulta;
import heath.med.NeoHealthCare.consulta.validacoes.ValidarAgendamento;
import heath.med.NeoHealthCare.dto.DetalhesConsulta;
import heath.med.NeoHealthCare.repository.ConsultaRepository;
import heath.med.NeoHealthCare.domain.medico.Medico;
import heath.med.NeoHealthCare.dto.AgendamentoConsultaDTO;
import heath.med.NeoHealthCare.exception.GenericException;
import heath.med.NeoHealthCare.repository.MedicoRepository;
import heath.med.NeoHealthCare.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultaService {

  @Autowired
  private ConsultaRepository consultaRepository;

  @Autowired
  private MedicoRepository medicoRepository;

  @Autowired
  private PacienteRepository pacienteRepository;

  @Autowired
  private List<ValidarAgendamento> validadores;

  public DetalhesConsulta agendar(AgendamentoConsultaDTO agendamentoConsulta) {
    if (!pacienteRepository.existsById(agendamentoConsulta.getIdPaciente())) {
      throw new GenericException("Paciente não encontrado");
    }

    if (agendamentoConsulta.getIdMedico() != null && !medicoRepository.existsById(agendamentoConsulta.getIdMedico())) {
      throw new GenericException("Médico não encontrado");
    }

    validadores.forEach(v -> v.validar(agendamentoConsulta));

    var paciente = pacienteRepository.getReferenceById(agendamentoConsulta.getIdPaciente());
    var medico = selecionarMedico(agendamentoConsulta);
    Consulta consulta = new Consulta(null, medico, paciente, agendamentoConsulta.getData(), null);
    consultaRepository.save(consulta);

    return new DetalhesConsulta(consulta);
  }

  private Medico selecionarMedico(AgendamentoConsultaDTO agendamentoConsulta) {
    if (agendamentoConsulta.getIdMedico() != null){
      return medicoRepository.getReferenceById(agendamentoConsulta.getIdMedico());
    }

    if(agendamentoConsulta.getEspecialidade() == null){
      throw new GenericException("Campo Especialidade Obrigatória");
    }
    return medicoRepository.randomMedico(agendamentoConsulta.getEspecialidade(), agendamentoConsulta.getData());
  }

  public void deletarConsulta(Long id, Cancelamento motivoCancelamento) {
    var consulta = consultaRepository.getReferenceById(id);
    consulta.setCancelamentoConsulta(motivoCancelamento);
    consultaRepository.save(consulta);
  }
}