package heath.med.apiHealth.controller;

import heath.med.apiHealth.domain.medico.DadosDetalhamentoMedico;
import heath.med.apiHealth.domain.medico.Medico;
import heath.med.apiHealth.domain.medico.MedicoRepository;
import heath.med.apiHealth.dto.MedicoUpdateDTO;
import heath.med.apiHealth.service.MedicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/v1")
public class Medicocontroller {

  @Autowired
  private MedicoRepository medicoRepository;

  @Autowired
  private MedicoService medicoService;

  @GetMapping("/medicos")
  public ResponseEntity<Page<Medico>> returnsMedico(@PageableDefault(size = 10, sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable) {
    return ResponseEntity.ok(medicoService.listarMedicos(pageable));
  }

  @PostMapping("/medicos")
  @Transactional
  public ResponseEntity cadastroMedico(@RequestBody @Valid Medico dadosDoctor, UriComponentsBuilder componentsBuilder) {
    var uri = componentsBuilder.path("/medidos/{id}").buildAndExpand(dadosDoctor.getId()).toUri();
    return ResponseEntity.created(uri).body(medicoService.cadastrarMedico(dadosDoctor));
  }

  @PatchMapping("/medicos/{id}")
  @Transactional
  public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody MedicoUpdateDTO medicoUpdateDTO) {
    return ResponseEntity.ok(new DadosDetalhamentoMedico(medicoService.atualizaMedico(id, medicoUpdateDTO)));
  }

  @DeleteMapping("/medicos/{id}")
  @Transactional
  public ResponseEntity delete(@PathVariable("id") Long id) {
    medicoService.deletarMedico(id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/medicos/{id}")
  public ResponseEntity detalhar(@PathVariable("id") Long id) {
    return ResponseEntity.ok(medicoService.detalharMedico(id));
  }
}