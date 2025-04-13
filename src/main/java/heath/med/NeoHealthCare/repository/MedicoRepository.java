package heath.med.NeoHealthCare.repository;

import heath.med.NeoHealthCare.domain.medico.Especialidade;
import heath.med.NeoHealthCare.domain.medico.Medico;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

  Page<Medico> findByAtivoTrue(Pageable pageable);

  @Query("""
    select m from Medico m
    where
    m.ativo = true
    and
    m.especialidade = :especialidade
    and
    m.id not in(
        select c.medico.id from Consulta c
        where
        c.data = :data
    )
    order by function('rand')
""")
  Medico randomMedico(Especialidade especialidade, @NotNull @Future LocalDateTime data);
}
