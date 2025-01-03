package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.api.domain.consulta.DatosCancelamientoConsulta;
import med.voll.api.domain.consulta.DatosReservaConsulta;
import med.voll.api.domain.consulta.ReservaConsultas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {

    @Autowired
    private ReservaConsultas reservaConsultas;

    @PostMapping
    @Transactional
    public ResponseEntity<?> reservar(@RequestBody DatosReservaConsulta datosReservaConsulta) {
        var detalleConsulta = reservaConsultas.reservar(datosReservaConsulta);
        return ResponseEntity.ok(detalleConsulta);
    }

    @DeleteMapping()
    @Transactional
    public ResponseEntity<?> cancelar(@RequestBody @Valid DatosCancelamientoConsulta datosCancelamientoConsulta) {
        reservaConsultas.cancelar(datosCancelamientoConsulta);
        return ResponseEntity.ok().build();
    }
}