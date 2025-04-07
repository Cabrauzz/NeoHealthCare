package heath.med.NeoHealthCare.domain.paciente;

import heath.med.NeoHealthCare.dto.PacienteUpdateDTO;
import heath.med.NeoHealthCare.domain.endereco.Endereco;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;


@Table(name = "Paciente")
@Entity(name = "Paciente")
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  private String nome;

  @NotBlank
  @Email
  private String email;

  @NotBlank
  private String telefone;

  @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}")
  private String cpf;

  @Embedded
  @Valid
  private Endereco endereco;

  private Boolean ativo = true;

  public Paciente(Long id, String nome, String email, String telefone, String cpf, Endereco endereco, Boolean ativo) {
    this.id = id;
    this.nome = nome;
    this.email = email;
    this.telefone = telefone;
    this.cpf = cpf;
    this.endereco = endereco;
    this.ativo = true;
  }

  public void updatePaciente(PacienteUpdateDTO pacienteUpdate) {
    if (pacienteUpdate.getNome() != null) {
      this.nome = pacienteUpdate.getNome();
    }
    if (pacienteUpdate.getTelefone() != null) {
      this.telefone = pacienteUpdate.getTelefone();
    }
    if (pacienteUpdate.getEndereco() != null) {
      this.endereco = pacienteUpdate.getEndereco();
    }
  }

  public void excluir() {
    this.ativo = false;
  }
}
