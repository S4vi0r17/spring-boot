package med.voll.api.domain.medico;

import jakarta.persistence.EntityManager;
import med.voll.api.domain.consulta.Consulta;
import med.voll.api.domain.medico.dto.CreateMedicoDto;
import med.voll.api.domain.medico.dto.Especialidad;
import med.voll.api.domain.paciente.Paciente;
import med.voll.api.domain.paciente.dto.CreatePacienteDto;
import med.voll.api.domain.shared.DireccionDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test") // Use the test database
class MedicoRepositoryTest {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    @DisplayName("Deberia devolver null cuadno el medico buscado existe pero no esta disponible en la fecha")
    void elegirMedicoAlAzarDisponibleEnEsaFechaEscenario1() {
        // given - arrange
        var lunesSiguienteALas10 = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);
        var medico = registrarMedico("Juan", "juan@gmail.com", "12345678", Especialidad.CARDIOLOGIA);
        var paciente = registrarPaciente("Pedro", "pedro@pedro.com", "12345678");
        registrarConsulta(medico, paciente, lunesSiguienteALas10);

        // when - act
        var medicoLibre = medicoRepository.elegirMedicoAlAzarDisponibleEnEsaFecha(lunesSiguienteALas10, Especialidad.CARDIOLOGIA);

        // then - assert
        assertThat(medicoLibre).isNull();
    }

    @Test
    @DisplayName("Deberia devolver un medico cuando el medico buscado esta disponible en la fecha")
    void elegirMedicoAlAzarDisponibleEnEsaFechaEscenario2() {
        // given - arrange
        var lunesSiguienteALas10 = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);
        var medico = registrarMedico("Juan", "juan@gmail.com", "12345678", Especialidad.CARDIOLOGIA);

        // when - act
        var medicoLibre = medicoRepository.elegirMedicoAlAzarDisponibleEnEsaFecha(lunesSiguienteALas10, Especialidad.CARDIOLOGIA);

        // then - assert
        assertThat(medicoLibre).isEqualTo(medico);
    }

    private void registrarConsulta(Medico medico, Paciente paciente, LocalDateTime fecha) {
        entityManager.persist(new Consulta(null, medico, paciente, fecha, null));
    }

    private Medico registrarMedico(String nombre, String email, String documento, Especialidad especialidad) {
        var medico = new Medico(datosMedico(nombre, email, documento, especialidad));
        entityManager.persist(medico);
        return medico;
    }

    private Paciente registrarPaciente(String nombre, String email, String documento) {
        var paciente = new Paciente(datosPaciente(nombre, email, documento));
        entityManager.persist(paciente);
        return paciente;
    }

    private CreateMedicoDto datosMedico(String nombre, String email, String documento, Especialidad especialidad) {
        return new CreateMedicoDto(
                nombre,
                email,
                "6145489789",
                documento,
                especialidad,
                datosDireccion()
        );
    }

    private CreatePacienteDto datosPaciente(String nombre, String email, String documento) {
        return new CreatePacienteDto(
                nombre,
                email,
                "1234564",
                documento,
                datosDireccion()
        );
    }

    private DireccionDto datosDireccion() {
        return new DireccionDto(
                "calle x",
                "distrito y",
                "ciudad z",
                "123",
                "1"
        );
    }
}
