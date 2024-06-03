package med.voll.api.domain.consulta.validacoes.agendamento;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosAngendamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoMedicoComConsultaEmHorarioIgual implements ValidadorAngedamentoDeConsulta {

    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar(DadosAngendamentoConsulta consulta){
        var possuiConsultaNaData = consultaRepository.existsByMedicoIdAndDataAndMotivoIsNull(consulta.idMedico(), consulta.data());
        if(possuiConsultaNaData){
            throw new ValidacaoException("Medico possui consulta nesse Horario!!!");
        }
    }
}
