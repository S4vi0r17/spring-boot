package med.voll.api.domain.medico;

import med.voll.api.domain.medico.dto.Especialidad;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Page<Medico> findByActivoTrue(Pageable paginacion);

    @Query("""
            select m from Medico m
            where
            m.activo = true
            and
            m.especialidad = :especialidad
            and
            m.id not in(
                select c.medico.id from Consulta c
                where
                c.fecha = :fecha
            and
                c.motivoCancelamiento is null
            )
            order by rand()
            limit 1
    """)
    Medico elegirMedicoAlAzarDisponibleEnEsaFecha(LocalDateTime fecha, Especialidad especialidad);

    @Query("""
            select m.activo
            from Medico m
            where m.id = :idMedico
            """)
    Boolean findActivoById(Long idMedico);
}
