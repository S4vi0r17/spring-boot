package med.voll.api.domain.consulta;

import med.voll.api.domain.ValidacionException;
import med.voll.api.domain.consulta.validaciones.ValidadorDeConsultas;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private List<ValidadorDeConsultas> validaciones;

    public void reservar(DatosReservaConsulta datosReservaConsulta) {

        if (!pacienteRepository.existsById(datosReservaConsulta.idPaciente())) {
            throw new ValidacionException("Paciente no encontrado con el id: " + datosReservaConsulta.idPaciente());
        }

        if ( datosReservaConsulta.idMedico() != null && !medicoRepository.existsById(datosReservaConsulta.idMedico())) {
            throw new ValidacionException("Médico no encontrado con el id: " + datosReservaConsulta.idMedico());
        }

        // validaciones
        validaciones.forEach(validador -> validador.validar(datosReservaConsulta));

        var medico = elegirMedico(datosReservaConsulta);
        var paciente = pacienteRepository.findById(datosReservaConsulta.idPaciente()).get();
        var consulta = new Consulta(null, medico, paciente, datosReservaConsulta.fecha(), null);
        consultaRepository.save(consulta);
    }

    private Medico elegirMedico(DatosReservaConsulta datosReservaConsulta) {
        if (datosReservaConsulta.idMedico() != null) {
            // El getReferenceById es la suma de los métodos get y findById
            return medicoRepository.getReferenceById(datosReservaConsulta.idMedico());
        }
        if (datosReservaConsulta.especialidad() == null) {
            throw new ValidacionException("Especialidad es requerida cuando no se especifica el médico");
        }

        return medicoRepository.elegirMedicoAlAzarDisponibleEnEsaFecha(datosReservaConsulta.fecha(), datosReservaConsulta.especialidad());
    }

    public void cancelar(DatosCancelamientoConsulta datosCacelamientoConsulta) {
        if (!consultaRepository.existsById(datosCacelamientoConsulta.idConsulta())) {
            throw new ValidacionException("Id de la consulta informado no existe!");
        }
        var consulta = consultaRepository.getReferenceById(datosCacelamientoConsulta.idConsulta());
        consulta.cancelar(datosCacelamientoConsulta.motivo());
    }
}
