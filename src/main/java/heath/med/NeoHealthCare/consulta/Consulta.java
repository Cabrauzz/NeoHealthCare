package heath.med.NeoHealthCare.consulta;

import heath.med.NeoHealthCare.domain.medico.Medico;
import heath.med.NeoHealthCare.domain.paciente.Paciente;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Table(name = "consultas")
@Entity(name = "Consulta")
public class Consulta {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "medico_id")
  private Medico medico;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "paciente_id")
  private Paciente paciente;

  private LocalDateTime data;

}
