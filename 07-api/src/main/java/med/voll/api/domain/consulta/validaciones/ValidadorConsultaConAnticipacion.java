package med.voll.api.domain.consulta.validaciones;

import med.voll.api.domain.ValidacionException;
import med.voll.api.domain.consulta.DatosReservaConsulta;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ValidadorConsultaConAnticipacion implements ValidadorDeConsultas {
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
