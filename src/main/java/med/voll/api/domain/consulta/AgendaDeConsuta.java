package med.voll.api.domain.consulta;


import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.validacoes.agendamento.ValidadorAngedamentoDeConsulta;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaDeConsuta {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private List<ValidadorAngedamentoDeConsulta> validadoresAngedamentoDeConsultas;

    public DadosDetalhamentoConsulta agendarConsulta(DadosAngendamentoConsulta consulta){

        if(!pacienteRepository.existsById(consulta.idPaciente())){
            throw new ValidacaoException("id do paciente informado não existe!!");
        }

        if(consulta.idMedico() != null && !medicoRepository.existsById(consulta.idMedico())){
            throw new ValidacaoException("id do medico informado não existe!!");
        }

        validadoresAngedamentoDeConsultas.forEach(v -> v.validar(consulta));

        var medico = escolherMedicoComAgendaLivre(consulta);

        var paciente = pacienteRepository.findById(consulta.idPaciente()).get();

        if(medico == null){
            throw new ValidacaoException("não existe medico disponivel nessa data.");
        }

        Consulta agendamento = new Consulta(null, medico, paciente, consulta.data(), null,null);

        consultaRepository.save(agendamento);

        return new DadosDetalhamentoConsulta(agendamento.getId(), agendamento.getPaciente().getId(), agendamento.getData());

    }

    private Medico escolherMedicoComAgendaLivre(DadosAngendamentoConsulta consulta){
        if(consulta.idMedico() != null){
           return medicoRepository.findById(consulta.idMedico()).get();
        }
        if(consulta.especialidade() == null){
            throw new ValidacaoException("especialidade não informada");
        }

        return medicoRepository.escolherMedicoComAgendaLivre(consulta.especialidade(), consulta.data());
    }

    public void cancelamentoConsulta(DadosCancelamentoConsulta cancelamento){
       var consulta = consultaRepository.buscarConsulta(cancelamento.medicoId(), cancelamento.pacienteId(), cancelamento.data());
        if (consulta == null) {
            throw new ValidacaoException("Consulta informada não existe!");
        }
       consulta.cancelar(cancelamento.motivo());
    }

}
