package med.voll.api.domain.consulta.validacoes.agendamento;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAngendamentoConsulta;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidacaoHorarioDeFuncionamento implements ValidadorAngedamentoDeConsulta {

    public void validar(DadosAngendamentoConsulta agendamento)
    {
        var dataConsulta = agendamento.data();
        var dataEDomingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var agendamentoAntesDaAbertura = dataConsulta.getHour() < 7;
        var agendamentoAposFechamento = dataConsulta.getHour() > 18;

        if(dataEDomingo || agendamentoAntesDaAbertura || agendamentoAposFechamento){
            throw new ValidacaoException("Agendamento fora do horario de funcionamento!!!");
        }
    }
}
