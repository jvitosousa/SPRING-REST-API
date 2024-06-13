package med.voll.api.domain.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.endereco.DadosCadastroEndereco;

public record DadosAtuliazarMedico(
        @NotNull
        Long id,
        String nome,
        @Email
        String email,
        String telefone,
        @Valid
        DadosCadastroEndereco dadosCadastroEndereco) {
}
