package heath.med.NeoHealthCare.Controller;

import heath.med.NeoHealthCare.domain.medico.Especialidade;
import heath.med.NeoHealthCare.dto.AgendamentoConsultaDTO;
import heath.med.NeoHealthCare.dto.DetalhesConsulta;
import heath.med.NeoHealthCare.service.ConsultaService;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ConsultaControllerTest {

  @Autowired
  private MockMvc mvc;

  @Autowired
  private JacksonTester<AgendamentoConsultaDTO> dadosAgendamento;

  @Autowired
  private JacksonTester<DetalhesConsulta> detalheConsulta;

  @MockitoBean
  private ConsultaService consultaService;



  @Test
  @Order(1)
  @WithMockUser
  void agendarConsultaErro400Test() throws Exception {
    var response = mvc.perform(post("/v1/consulta"))
        .andReturn()
        .getResponse();

    assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
  }

  @Test
  @Order(2)
  @WithMockUser
  void agendarConsulta200Test() throws Exception {
    var data = LocalDateTime.now().plusHours(1);
    Especialidade especialidade = Especialidade.valueOf("CARDIOLOGIA");

    var dadosDetalhamento = new DetalhesConsulta(null, 2l, 5l, data);

    when(consultaService.agendar(any())).thenReturn(new DetalhesConsulta(null, 3l, 8l, data));

    var response = mvc.perform(post("/v1/consulta")
            .contentType(MediaType.APPLICATION_JSON)
            .content(dadosAgendamento.write(new AgendamentoConsultaDTO(null, 3l, 8l, data, especialidade)).getJson()))
        .andReturn()
        .getResponse();

    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

    var json = detalheConsulta.write(new DetalhesConsulta(null, 3l, 8l, data)).getJson();

    assertEquals(response.getContentAsString(), json);

  }
}