package heath.med.NeoHealthCare.controller;

import heath.med.NeoHealthCare.consulta.CancelamentoConsulta;
import heath.med.NeoHealthCare.dto.AgendamentoConsultaDTO;
import heath.med.NeoHealthCare.dto.DetalhesConsulta;
import heath.med.NeoHealthCare.service.ConsultaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/consultas")
public class ConsultaController {

  @Autowired
  private ConsultaService consultaService;

  @PostMapping
  @Transactional
  public ResponseEntity agendamento(@RequestBody @Valid AgendamentoConsultaDTO consulta){
    DetalhesConsulta agendar = consultaService.agendar(consulta);
    return ResponseEntity.ok(agendar);
  }

  @DeleteMapping("/canceled")
  @Transactional
  public ResponseEntity cancelar(@RequestBody @Valid CancelamentoConsulta cancelamentoConsulta){
    consultaService.deletarConsulta(cancelamentoConsulta.getIdConsulta());
    return ResponseEntity.noContent().build();
  }
}
