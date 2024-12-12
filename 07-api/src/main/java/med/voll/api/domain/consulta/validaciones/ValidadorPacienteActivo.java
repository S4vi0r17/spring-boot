package med.voll.api.domain.consulta.validaciones;

import med.voll.api.domain.ValidacionException;
import med.voll.api.domain.consulta.DatosReservaConsulta;
import med.voll.api.domain.paciente.PacienteRepository;

public class ValidacionPacienteActivo {

    private PacienteRepository pacienteRepository;

    public void validar(DatosReservaConsulta datosReservaConsulta) {
        var paciente = pacienteRepository.findActivoById(datosReservaConsulta.idPaciente());

        if (!paciente) {
            throw new ValidacionException("El paciente no está activo");
        }
    }
}
