package med.voll.api.domain.medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Page<Medico> findAllByAtivoTrue(Pageable pagina);

    @Query("""
        select m from Medicos m
        where m.ativo = true
        and m.especialidade = :especialidade
        and m.id not in(
            select c from Consulta c
            where c.data = :data
            and c.cancelada = false
        )
        order by rand()
        limit 1
    """)
    Medico escolherMedicoComAgendaLivre(Especialidade especialidade, LocalDateTime data);

    @Query("""
    select m.ativo from Medicos m
    where m.id = :aLong
""")
    boolean verificaMedicoAtivo(Long aLong);
}
