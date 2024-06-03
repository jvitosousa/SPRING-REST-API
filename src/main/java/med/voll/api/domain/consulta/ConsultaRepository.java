package med.voll.api.domain.consulta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    @Query("""
    select c from Consulta c
    where c.medico.id = :medicoId
    and c.paciente.id = :pacienteId
    and c.data = :data
""")

    Consulta buscarConsulta(Long medicoId, Long pacienteId, LocalDateTime data);

    boolean existsByPacienteIdAndData(Long aLong, LocalDateTime data);

    boolean existsByMedicoIdAndDataAndMotivoIsNull(Long aLong, LocalDateTime data);

}

