package med.voll.api.domain.consulta.validacoes.cancelamento;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosCancelamentoConsulta;
import med.voll.api.domain.consulta.MotivoCancelamento;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoMotivoCancelamentoConsulta implements ValidadorCancelamentoDeConsulta {

    public void validar(DadosCancelamentoConsulta cancelamentoConsulta){
        if(!cancelamentoConsulta.motivo().equals(MotivoCancelamento.OUTROS)
                || !cancelamentoConsulta.equals(MotivoCancelamento.MEDICO_CANCELOU)
                || !cancelamentoConsulta.equals(MotivoCancelamento.PACIENTE_DESISTIU))
        {
            throw new ValidacaoException("motivo de cancelamento invalido!!!");
        }
    }

}
