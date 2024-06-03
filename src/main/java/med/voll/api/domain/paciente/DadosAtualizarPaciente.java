package med.voll.api.domain.paciente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.endereco.DadosCadastroEndereco;

public record DadosAtualizarPaciente(
        @NotNull
        Long id,

        String nome,
        @Email
        String email,

        DadosCadastroEndereco endereco) {
}
