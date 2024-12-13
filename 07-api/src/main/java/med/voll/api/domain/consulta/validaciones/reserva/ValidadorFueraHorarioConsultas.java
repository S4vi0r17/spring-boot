package med.voll.api.domain.consulta.validaciones.reserva;

import med.voll.api.domain.ValidacionException;
import med.voll.api.domain.consulta.DatosReservaConsulta;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidadorFueraHorarioConsultas implements ValidadorDeConsultas {
    public void validar(DatosReservaConsulta datosReservaConsulta) {
        var fechaConsulta = datosReservaConsulta.fecha();
        var domingo = fechaConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var horarioApertura = fechaConsulta.getHour() < 7;
        var horarioCierre = fechaConsulta.getHour() > 18;

        if (domingo || horarioApertura || horarioCierre) {
            throw new ValidacionException("No se pueden reservar consultas los domingos ni fuera del horario de atención");
        }
    }
}
