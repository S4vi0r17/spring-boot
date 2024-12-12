package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.consulta.DatosCancelamientoConsulta;
import med.voll.api.domain.consulta.DatosDetalleConsulta;
import med.voll.api.domain.consulta.DatosReservaConsulta;
import med.voll.api.domain.consulta.ReservaConsultas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private ReservaConsultas reservaConsultas;

    @PostMapping
    @Transactional
    public ResponseEntity<?> reservar(@RequestBody DatosReservaConsulta datosReservaConsulta) {
        reservaConsultas.reservar(datosReservaConsulta);
        return ResponseEntity.ok(new DatosDetalleConsulta(null, null, null, null));
    }

    @DeleteMapping()
    @Transactional
    public ResponseEntity<?> cancelar(@RequestBody @Valid DatosCancelamientoConsulta datosCancelamientoConsulta) {
        reservaConsultas.cancelar(datosCancelamientoConsulta);
        return ResponseEntity.ok().build();
    }
}