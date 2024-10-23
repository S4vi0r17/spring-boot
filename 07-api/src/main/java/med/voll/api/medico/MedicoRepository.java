package med.voll.api.medico.repository;

import med.voll.api.medico.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<Medico, Long> { }
