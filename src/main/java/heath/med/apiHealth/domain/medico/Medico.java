package heath.med.apiHealth.domain.medico;

import heath.med.apiHealth.dto.MedicoUpdateDTO;
import heath.med.apiHealth.domain.endereco.Endereco;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @NotBlank(message = "Campo nome obrigatório")
  private String nome;

  @NotBlank(message = "Campo email obrigatório")
  @Email(message = "Formato incorreto")
  private String email;

  @NotBlank(message = "Campo telefone obrigatório")
  private String telefone;

  @NotBlank
  @Pattern(regexp = "\\d{4,6}")
  private String crm;

  @Enumerated(EnumType.STRING)
  @NotNull
  private Especialidade especialidade;

  @Embedded
  @NotNull
  @Valid
  private Endereco endereco;

  private Boolean ativo;

  public Medico(Long id, String nome, String email, String telefone, String crm, Especialidade especialidade, Endereco endereco, Boolean ativo) {
    this.id = id;
    this.nome = nome;
    this.email = email;
    this.telefone = telefone;
    this.crm = crm;
    this.especialidade = especialidade;
    this.endereco = endereco;
    this.ativo = true;
  }

  public void atualizarInformacao(@Valid MedicoUpdateDTO medicoUpdateDTO) {
    if (medicoUpdateDTO.getNome() != null) {
      this.nome = medicoUpdateDTO.getNome();
    }
    if (medicoUpdateDTO.getTelefone() != null) {
      this.telefone = medicoUpdateDTO.getTelefone();
    }
    if (medicoUpdateDTO.getEndereco() != null) {
      this.endereco.atualizarEndereco(medicoUpdateDTO.getEndereco());
    }
  }

  public void excluir() {
    this.ativo = false;
  }
}
