package med.voll.api.domain.consulta.validaciones.reserva;

import med.voll.api.domain.ValidacionException;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DatosReservaConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacientesSinOtraConsultaEnElMismoDia implements ValidadorDeConsultas{

    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar(DatosReservaConsulta datosReservaConsulta) {
        var primerHorario = datosReservaConsulta.fecha().withHour(7);
        var ultimoHorario = datosReservaConsulta.fecha().withHour(18);

        if (consultaRepository.existsByPacienteIdAndFechaBetween(datosReservaConsulta.idPaciente(), primerHorario, ultimoHorario)) {
            throw new ValidacionException("El paciente ya tiene una consulta reservada para el mismo día");
        }
    }
}
