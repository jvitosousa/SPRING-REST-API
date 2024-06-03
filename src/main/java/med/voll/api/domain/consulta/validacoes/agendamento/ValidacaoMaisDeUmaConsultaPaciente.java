package med.voll.api.domain.consulta.validacoes.agendamento;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosAngendamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoMaisDeUmaConsultaPaciente implements ValidadorAngedamentoDeConsulta {

    @Autowired
    ConsultaRepository consultaRepository;
    public void validar(DadosAngendamentoConsulta agendamento){

        var existeConsultaNoMesmoDia = consultaRepository.existsByPacienteIdAndData(agendamento.idPaciente(),agendamento.data());

        if (existeConsultaNoMesmoDia){
            throw new ValidacaoException("Paciente ja tem um consulta nessa data");
        }
    }
}
