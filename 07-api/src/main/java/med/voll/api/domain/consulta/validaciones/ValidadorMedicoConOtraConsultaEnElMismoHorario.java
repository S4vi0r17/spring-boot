package med.voll.api.domain.consulta.validaciones;

import med.voll.api.domain.ValidacionException;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DatosReservaConsulta;

public class ValidacionMedicoConOtraConsultaEnElMismoHorario {
    private ConsultaRepository consultaRepository;

    public void validar(DatosReservaConsulta datosReservaConsulta) {
        if (consultaRepository.existsByMedicoIdAndFecha(datosReservaConsulta.idMedico(), datosReservaConsulta.fecha())) {
            throw new ValidacionException("El médico ya tiene otra consulta en el mismo horario");
        }
    }
}
