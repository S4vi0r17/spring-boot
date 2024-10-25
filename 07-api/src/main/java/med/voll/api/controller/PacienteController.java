package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.paciente.Paciente;
import med.voll.api.paciente.PacienteRepository;
import med.voll.api.paciente.dto.CreatePacienteDto;
import med.voll.api.paciente.dto.ListPacienteDto;
import med.voll.api.paciente.dto.UpdatePacienteDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepository;

    @PostMapping
    @Transactional // sirve para que se haga un rollback si hay un error
    public void registrar(@RequestBody @Valid CreatePacienteDto datos) {
        pacienteRepository.save(new Paciente(datos));
    }

    @GetMapping
    public Page<ListPacienteDto> list(@PageableDefault(sort = {"nombre"}) Pageable pageable) {
        // page, size, sort
        return pacienteRepository.findAll(pageable).map(ListPacienteDto::new);
    }

    @PutMapping
    @Transactional
    public void actualizar(@RequestBody @Valid UpdatePacienteDto updatePacienteDto) {
        Paciente paciente = pacienteRepository.getReferenceById(updatePacienteDto.id());
        paciente.update(updatePacienteDto);
        pacienteRepository.save(paciente);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable Long id) {
        pacienteRepository.deleteById(id);
    }
}