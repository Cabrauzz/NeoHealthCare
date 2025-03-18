package heath.med.apiHealth.domain.medico;

import heath.med.apiHealth.domain.endereco.Endereco;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoMedico(
    @NotNull
    Long id,
    String nome,
    String telefone,
    Endereco endereco) {
}
