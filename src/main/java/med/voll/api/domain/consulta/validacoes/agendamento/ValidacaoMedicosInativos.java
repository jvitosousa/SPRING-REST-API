package med.voll.api.domain.consulta.validacoes.agendamento;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAngendamentoConsulta;
import med.voll.api.domain.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoMedicosInativos implements ValidadorAngedamentoDeConsulta {

    @Autowired
    MedicoRepository medicoRepository;
    public void validar(DadosAngendamentoConsulta agendamento){

        if(agendamento.idMedico() == null){
            return;
        }

        var medicoAtivo = medicoRepository.verificaMedicoAtivo(agendamento.idMedico());

        if(!medicoAtivo){
          throw new ValidacaoException("medico esta inativo");
        }
    }
}
