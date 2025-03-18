package heath.med.apiHealth.domain.endereco;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Embeddable
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Endereco {

  @NotBlank
  private String logadouro;
  @NotBlank
  private String bairro;
  @NotBlank
  @Pattern(regexp = "\\d{5}-\\d{3}")
  private String cep;
  private String numero;
  private String complemento;
  @NotBlank
  private String cidade;
  @NotBlank
  private String uf;

  public Endereco(Endereco endereco) {
    this.logadouro = endereco.logadouro;
    this.bairro = endereco.bairro;
    this.cep = endereco.cep;
    this.numero = endereco.numero;
    this.complemento = endereco.complemento;
    this.cidade = endereco.cidade;
    this.uf = endereco.uf;
  }

  public void atualizarEndereco(Endereco endereco) {
    if(endereco.getLogadouro() != null) {
      this.logadouro = endereco.logadouro;
    }

    if(endereco.getCep() != null) {
      this.cep = endereco.getCep();
    }

    if(endereco.getNumero() != null) {
      this.numero = endereco.getNumero();
    }

    if(endereco.getComplemento() != null) {
      this.complemento = endereco.getComplemento();
    }

    if(endereco.getBairro() != null) {
      this.bairro = endereco.getBairro();
    }

    if(endereco.getCidade() != null) {
      this.cidade = endereco.getCidade();
    }

    if(endereco.getUf() != null) {
      this.uf = endereco.getUf();
    }
  }
}
