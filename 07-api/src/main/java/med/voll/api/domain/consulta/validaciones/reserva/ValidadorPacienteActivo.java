package med.voll.api.domain.consulta.validaciones.reserva;

import med.voll.api.domain.ValidacionException;
import med.voll.api.domain.consulta.DatosReservaConsulta;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteActivo implements ValidadorDeConsultas {

    @Autowired
    private PacienteRepository pacienteRepository;

    public void validar(DatosReservaConsulta datosReservaConsulta) {
        var paciente = pacienteRepository.findActivoById(datosReservaConsulta.idPaciente());

        if (!paciente) {
            throw new ValidacionException("El paciente no está activo");
        }
    }
}
