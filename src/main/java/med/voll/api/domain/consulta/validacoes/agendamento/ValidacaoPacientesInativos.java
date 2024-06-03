package med.voll.api.domain.consulta.validacoes.agendamento;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAngendamentoConsulta;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoPacientesInativos implements ValidadorAngedamentoDeConsulta {

    @Autowired
    PacienteRepository pacienteRepository;
    public void validar(DadosAngendamentoConsulta agendamento){
        var pacienteEstaAtivo = pacienteRepository.getPacienteEstaAtivo(agendamento.idPaciente());

        if(!pacienteEstaAtivo){
            throw new ValidacaoException("paciente está inativo");
        }
    }
}
