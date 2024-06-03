package med.voll.api.domain.paciente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.endereco.DadosCadastroEndereco;

public record DadosCadastroPaciente(

        @NotNull
        @NotBlank
        String nome,

        @NotBlank
        @NotNull
        @Email
        String email,

        @NotBlank
        @NotNull
        @Pattern(regexp = "\\d{11}")
        String cpf,

        @Valid
        DadosCadastroEndereco endereco) {


}
