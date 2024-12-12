package med.voll.api.domain.consulta.validaciones;

import med.voll.api.domain.ValidacionException;
import med.voll.api.domain.consulta.DatosReservaConsulta;

import java.time.Duration;
import java.time.LocalDateTime;

public class ValidacionConsultaConAnticipacion {
    public void validar(DatosReservaConsulta datosReservaConsulta) {
        var fechaConsulta = datosReservaConsulta.fecha();
        var ahora = LocalDateTime.now();
        // var diferenciaEnMinutos = Duration.between(ahora, fechaConsulta).toMinutes();
        var diferenciaEnMinutos = ahora.until(fechaConsulta, java.time.temporal.ChronoUnit.MINUTES);

        if (diferenciaEnMinutos < 30) {
            throw new ValidacionException("La consulta debe ser reservada con al menos 30 minutos de anticipación");
        }
    }
}
