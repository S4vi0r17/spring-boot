package med.voll.api.domain.consulta.validaciones;

import med.voll.api.domain.ValidacionException;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DatosReservaConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoConOtraConsultaEnElMismoHorario implements ValidadorDeConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar(DatosReservaConsulta datosReservaConsulta) {
        if (consultaRepository.existsByMedicoIdAndFecha(datosReservaConsulta.idMedico(), datosReservaConsulta.fecha())) {
            throw new ValidacionException("El médico ya tiene otra consulta en el mismo horario");
        }
    }
}
