package med.voll.api.domain.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record DadosLogin(
        @NotNull
        @Email
        String login,
        @NotNull
        String senha) {


}
