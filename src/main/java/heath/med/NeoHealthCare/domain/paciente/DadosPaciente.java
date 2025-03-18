//package heath.med.apiHealth.paciente;
//
//import heath.med.apiHealth.domain.endereco.Endereco;
//import jakarta.persistence.Embedded;
//import jakarta.validation.Valid;
//import jakarta.validation.constraints.Email;
//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.Pattern;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//public class DadosPaciente {
//
//  @NotBlank
//  private String nome;
//
//  @NotBlank
//  @Email
//  private String email;
//
//  @NotBlank
//  private String telefone;
//
//  @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}")
//  private String cpf;
//
//  @Embedded
//  @Valid
//  private Endereco endereco;
//
//  public DadosPaciente(Paciente paciente) {
//    this.nome = paciente.getNome();
//    this.email = paciente.getEmail();
//    this.telefone = paciente.getTelefone();
//    this.cpf = paciente.getCpf();
//    this.endereco = paciente.getEndereco();
//  }
//}
