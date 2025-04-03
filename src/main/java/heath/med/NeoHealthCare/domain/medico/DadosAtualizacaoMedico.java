package heath.med.NeoHealthCare.domain.medico;

import heath.med.NeoHealthCare.domain.endereco.Endereco;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoMedico(
    @NotNull
    Long id,
    String nome,
    String telefone,
    Endereco endereco) {
}
