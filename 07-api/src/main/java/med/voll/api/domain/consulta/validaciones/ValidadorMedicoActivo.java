package med.voll.api.domain.consulta.validaciones;

import med.voll.api.domain.ValidacionException;
import med.voll.api.domain.consulta.DatosReservaConsulta;
import med.voll.api.domain.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoActivo implements ValidadorDeConsultas {

    @Autowired
    private MedicoRepository medicoRepository;

    public void validar(DatosReservaConsulta datosReservaConsulta) {
        // Eleccion medico opcional
        if (datosReservaConsulta.idMedico() == null) {
            return;
        }

        var medico = medicoRepository.findActivoById(datosReservaConsulta.idMedico());
        if (!medico) {
            throw new ValidacionException("El médico no está activo");
        }
    }
}
