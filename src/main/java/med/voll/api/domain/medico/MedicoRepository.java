package med.voll.api.domain.medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Page<Medico> findAllByAtivoTrue(Pageable pagina);

    @Query("""
        select m from Medico m
            where m.ativo = true
            and m.especialidade = :especialidade
            and not exists (
                select c from Consulta c
                where c.medico.id = m.id
                and c.data = :data
                and c.cancelada = false
            )
            order by function('rand')
    """)
    Medico escolherMedicoComAgendaLivre(@Param("especialidade") Especialidade especialidade, @Param("data")  LocalDateTime data);

    @Query("""
    select m.ativo from Medico m
    where m.id = :aLong
""")
    boolean verificaMedicoAtivo(Long aLong);
}
