package med.voll.api.domain.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroEndereco(
        @NotBlank
        @Pattern(regexp = "\\d{8}")
        String CEP,
        @NotBlank
        String UF,
        @NotBlank
        String cidade,
        @NotBlank
        String logradouro,
        @NotBlank
        String bairro,
        String numero,
        String complemento) {
}
