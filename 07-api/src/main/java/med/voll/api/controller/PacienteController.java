package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.api.domain.paciente.Paciente;
import med.voll.api.domain.paciente.PacienteRepository;
import med.voll.api.domain.paciente.dto.CreatePacienteDto;
import med.voll.api.domain.paciente.dto.ListPacienteDto;
import med.voll.api.domain.paciente.dto.PacienteResponse;
import med.voll.api.domain.paciente.dto.UpdatePacienteDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("pacientes")
@SecurityRequirement(name = "bearer-key")
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepository;

    @PostMapping
    @Transactional // sirve para que se haga un rollback si hay un error
    public ResponseEntity<PacienteResponse> registrar(@RequestBody @Valid CreatePacienteDto datos) {
        Paciente paciente = pacienteRepository.save(new Paciente(datos));
        PacienteResponse pacienteResponse = new PacienteResponse(
                paciente.getId(),
                paciente.getNombre(),
                paciente.getDocumentoIdentidad(),
                paciente.getEmail()
        );

        URI uri = UriComponentsBuilder.fromPath("/pacientes/{id}")
                .buildAndExpand(paciente.getId())
                .toUri();

        return ResponseEntity.created(uri).body(pacienteResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteResponse> get(@PathVariable Long id) {
        Paciente paciente = pacienteRepository.getReferenceById(id);
        PacienteResponse pacienteResponse = new PacienteResponse(
                paciente.getId(),
                paciente.getNombre(),
                paciente.getDocumentoIdentidad(),
                paciente.getEmail()
        );
        return ResponseEntity.ok(pacienteResponse);
    }

    @GetMapping
    public ResponseEntity<Page<ListPacienteDto>> list(@PageableDefault(sort = {"nombre"}) Pageable pageable) {
        // page, size, sort
        var pacientes = pacienteRepository.findAll(pageable).map(ListPacienteDto::new);
        return ResponseEntity.ok(pacientes);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<PacienteResponse> actualizar(@RequestBody @Valid UpdatePacienteDto updatePacienteDto) {
        Paciente paciente = pacienteRepository.getReferenceById(updatePacienteDto.id());
        paciente.update(updatePacienteDto);
        pacienteRepository.save(paciente);
        return ResponseEntity.ok(new PacienteResponse(
                paciente.getId(),
                paciente.getNombre(),
                paciente.getDocumentoIdentidad(),
                paciente.getEmail()
        ));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Map<String,String>> delete(@PathVariable Long id) {
        pacienteRepository.deleteById(id);

        Map<String, String> response = new HashMap<>();

        response.put("mensaje", "El paciente con el ID " + id + " ha sido eliminado correctamente.");

        return ResponseEntity.ok(response);
    }
}
