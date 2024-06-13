package med.voll.api.domain.medico;

import med.voll.api.domain.consulta.Consulta;
import med.voll.api.domain.endereco.DadosCadastroEndereco;
import med.voll.api.domain.paciente.DadosCadastroPaciente;
import med.voll.api.domain.paciente.Paciente;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class MedicoRepositoryTest {

    @Autowired
    MedicoRepository medicoRepository;

    @Autowired
    TestEntityManager em;

    @Test
    @DisplayName("Deve retornar null quando unico medico cadastrado nao esta disponivel")
    void escolherMedicoComAgendaLivreFail() {

        var paciente = cadastrarPaciente("Paciente", "paciente@gmail.com", "1234567891");
        var medico = cadastrarMedico("Medico", "medico@gmail.com", "123456", Especialidade.CARDIOLOGIA);

        em.persistAndFlush(paciente);
        em.persistAndFlush(medico);


        var proximaSegundaAs10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.SUNDAY))
                .atTime(10, 0);

        var consulta = cadastrarConsulta(medico, paciente, proximaSegundaAs10);
        em.persistAndFlush(consulta);

        var medicoLivre = medicoRepository.escolherMedicoComAgendaLivre(Especialidade.CARDIOLOGIA, proximaSegundaAs10);

        assertThat(medicoLivre).isNull();
    }


    @Test
    @DisplayName("Deve retornar o medico quando medico cadastrado esta disponivel")
    void escolherMedicoComAgendaLivre() {

        var paciente = cadastrarPaciente("Paciente", "paciente@gmail.com", "1234567891");
        var medico = cadastrarMedico("Medico", "medico@gmail.com", "123456", Especialidade.CARDIOLOGIA);

        em.persistAndFlush(paciente);
        em.persistAndFlush(medico);


        var proximaSegundaAs10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.SUNDAY))
                .atTime(10, 0);

        var medicoLivre = medicoRepository.escolherMedicoComAgendaLivre(Especialidade.CARDIOLOGIA, proximaSegundaAs10);

        assertThat(medicoLivre).isEqualTo(medico);
    }



    private Consulta cadastrarConsulta(Medico medico, Paciente paciente, LocalDateTime data) {
        var consulta = new Consulta(null , medico, paciente, data, false, null);
        em.persist(consulta);
        return consulta;
    }

    private Medico cadastrarMedico(String nome, String email, String crm, Especialidade especialidade) {
        var medico = new Medico(dadosMedico(nome, email, crm, especialidade));
        em.persist(medico);
        return medico;
    }

    private Paciente cadastrarPaciente(String nome, String email, String cpf) {
        var paciente = new Paciente(dadosPaciente(nome, email, cpf));
        em.persist(paciente);
        return paciente;
    }

    private DadosCadastroMedico dadosMedico(String nome, String email, String crm, Especialidade especialidade) {
        return new DadosCadastroMedico(
                nome,
                email,
                "61999999999",
                crm,
                especialidade,
                dadosEndereco()
        );
    }

    private DadosCadastroPaciente dadosPaciente(String nome, String email, String cpf) {
        return new DadosCadastroPaciente(
                nome,
                email,
                cpf,
                dadosEndereco()
        );
    }

    private DadosCadastroEndereco dadosEndereco() {
        return new DadosCadastroEndereco(
                "rua xpto",
                "MG",
                "00000000",
                "Brasilia",
                "DF",
                null,
                null
        );
    }
}