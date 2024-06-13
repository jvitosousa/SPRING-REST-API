package med.voll.api.domain.consulta;

import java.time.LocalDateTime;

public record DadosDetalhamentoConsulta(
        Long idMedico,
        Long idPaciente,

        LocalDateTime data
) {
    public DadosDetalhamentoConsulta(Consulta consulta){
        this(consulta.getId(), consulta.getPaciente().getId(), consulta.getData());
    }
}
