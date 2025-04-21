package heath.med.NeoHealthCare.controller;


import heath.med.NeoHealthCare.dto.PacienteUpdateDTO;
import heath.med.NeoHealthCare.domain.paciente.DadosDetalhamentoPaciente;
import heath.med.NeoHealthCare.domain.paciente.Paciente;
import heath.med.NeoHealthCare.repository.PacienteRepository;
import heath.med.NeoHealthCare.service.PacienteService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/v1")
@SecurityRequirement(name = "bearer-key")
public class PacienteController {

  @Autowired
  private PacienteRepository pacienteRepository;

  @Autowired
  private PacienteService pacienteService;

  @GetMapping("/pacientes")
  public ResponseEntity<Page<Paciente>> returnsPaciente(@PageableDefault(size = 10, sort = {"nome"}) Pageable pageable) {
    return ResponseEntity.ok(pacienteService.ListarPaciente(pageable));
  }

  @PostMapping("/pacientes")
  @Transactional
  public ResponseEntity cadastroPaciente(@RequestBody @Valid Paciente dadosPaciente, UriComponentsBuilder builder) {
    var uriPaciente = builder.path("/pacientes/{id}").buildAndExpand(dadosPaciente.getId()).toUri();
    return ResponseEntity.created(uriPaciente).body(pacienteService.cadastroPaciente(dadosPaciente));
  }

  @PatchMapping("/pacientes/{id}")
  @Transactional
  public ResponseEntity atualizaPaciente(@PathVariable("id") Long id, @RequestBody PacienteUpdateDTO pacienteUpdate) {
    var loadPaciente = pacienteRepository.getReferenceById(id);
    loadPaciente.updatePaciente(pacienteUpdate);
    return ResponseEntity.ok(new DadosDetalhamentoPaciente(pacienteRepository.save(loadPaciente)));
  }

  @DeleteMapping("/pacientes/{id}")
  public ResponseEntity delete(@PathVariable("id") Long id) {
    pacienteService.deletePaciente(id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/pacientes/{id}")
  public ResponseEntity detalhar(@PathVariable("id") Long id) {
    pacienteService.detalharPaciente(id);
    return ResponseEntity.ok(new DadosDetalhamentoPaciente(pacienteService.detalharPaciente(id)));
  }
}