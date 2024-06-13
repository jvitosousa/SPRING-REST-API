package med.voll.api.domain.consulta.validacoes.agendamento;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAngendamentoConsulta;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidacaoAgendamentoHorarioAtencedente implements ValidadorAngedamentoDeConsulta {

    public void validar(DadosAngendamentoConsulta agendamento){
        var dataAgendamento = agendamento.data();
        var dataDeAgora = LocalDateTime.now();

        var diferencaEmMinutos = Duration.between(dataDeAgora, dataAgendamento).toMinutes();

        if(diferencaEmMinutos < 30){
            throw new ValidacaoException("O agendamento deve ser feito com 30 min de atencedencia!");
        }

    }


}
