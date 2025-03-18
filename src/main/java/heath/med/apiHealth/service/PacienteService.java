package heath.med.apiHealth.service;

import heath.med.apiHealth.domain.paciente.DadosDetalhamentoPaciente;
import heath.med.apiHealth.domain.paciente.Paciente;
import heath.med.apiHealth.domain.paciente.PacienteRepository;
import heath.med.apiHealth.dto.PacienteUpdateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PacienteService {

  @Autowired
  private PacienteRepository pacienteRepository;

  public Page<Paciente> ListarPaciente(Pageable pageable){
    return  pacienteRepository.findByAtivoTrue(pageable);
  }

  public Paciente cadastroPaciente(Paciente paciente){
    pacienteRepository.save(paciente);
    return paciente;
  }

  public Paciente updatePaciente(Long id, PacienteUpdateDTO pacienteUpdateDTO){
    var loadPaciente = pacienteRepository.getReferenceById(id);
    loadPaciente.updatePaciente(pacienteUpdateDTO);
    return pacienteRepository.save(loadPaciente);
  }

  public void deletePaciente(Long id){
    var load = pacienteRepository.getReferenceById(id);
    load.excluir();
    pacienteRepository.save(load);
  }

  public Paciente detalharPaciente(Long id){
    var load = pacienteRepository.getReferenceById(id);
    return pacienteRepository.save(load);
  }
}
