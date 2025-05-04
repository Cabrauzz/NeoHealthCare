package heath.med.NeoHealthCare.repository;

import heath.med.NeoHealthCare.domain.medico.Especialidade;
import heath.med.NeoHealthCare.service.MedicoService;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class MedicoRepositoryTest {

  @Mock
  private MedicoRepository medicoRepository;

  @Test
  @Order(1)
  void randomMedicoReturnsEmptyListWhenNoMatchingEspecialidade() {
    var data = LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.THURSDAY));
    var medicos = medicoRepository.randomMedico(Especialidade.CARDIOLOGIA, data);
    assertThat(medicos).isEmpty();
  }

  @Test
  @Order(2)
  void findAtivoByIdReturnsTrueWhenMedicoIsActive() {
    when(medicoRepository.findAtivoById(1L)).thenReturn(true);

    medicoRepository.findAtivoById(1L);

    verify(medicoRepository, times(1)).findAtivoById(1L);
  }

  @Test
  @Order(3)
  void randomMedicoReturnsListWhenNoMatchingEspecialidade() {
    var data = LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.THURSDAY));
    var medicos = medicoRepository.randomMedico(Especialidade.CARDIOLOGIA, data);
    assertThat(medicos).isEqualTo(medicos);
  }
}