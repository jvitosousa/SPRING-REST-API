package med.voll.api.domain.consulta.validacoes.cancelamento;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosCancelamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidacaoCancelamentoDeConsulta24hrsAntes implements ValidadorCancelamentoDeConsulta {

    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar(DadosCancelamentoConsulta cancelamentoConsulta) {
        var consulta = consultaRepository.buscarConsulta(cancelamentoConsulta.medicoId(), cancelamentoConsulta.pacienteId(), cancelamentoConsulta.data());
        LocalDateTime dataHoje = LocalDateTime.now();

        var diferenEmHoras = Duration.between(dataHoje, consulta.getData()).toHours();

        if(diferenEmHoras < 24){
            throw new ValidacaoException("Consulta somente pode ser cancelada com antecedência mínima de 24h!");
        }
    }
}
