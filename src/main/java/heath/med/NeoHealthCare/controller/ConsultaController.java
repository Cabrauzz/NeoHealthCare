package heath.med.NeoHealthCare.controller;

import heath.med.NeoHealthCare.dto.AgendamentoConsultaDTO;
import heath.med.NeoHealthCare.dto.DetalhesConsulta;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/consultas")
public class ConsultaController {

  @PostMapping
  @Transactional
  public ResponseEntity agendamento(@RequestBody @Valid AgendamentoConsultaDTO consulta){
    return ResponseEntity.ok(new DetalhesConsulta(null, null , null, null));
  }
}
