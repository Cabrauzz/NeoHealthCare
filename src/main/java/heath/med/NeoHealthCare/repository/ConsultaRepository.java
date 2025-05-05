package heath.med.NeoHealthCare.repository;

import heath.med.NeoHealthCare.consulta.Consulta;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

  Boolean existsByMedicoIdAndData(Long idMedico, @NotNull @Future LocalDateTime data);

  Boolean existsByPacienteIdAndDataBetween(@NotNull Long idPaciente, LocalDateTime f1rstHour, LocalDateTime lastHour);

}

