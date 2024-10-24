package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.medico.MedicoRepository;
import med.voll.api.medico.dto.CreateMedicoDto;
import med.voll.api.medico.Medico;
import med.voll.api.medico.dto.ListMedicoDto;
import med.voll.api.medico.dto.UpdateMedicoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping
    public void create(@RequestBody @Valid CreateMedicoDto createMedicoDto) {
        medicoRepository.save(new Medico(createMedicoDto));
    }

    @GetMapping
    public Page<ListMedicoDto> list(@PageableDefault(size = 2) Pageable paginacion) {
        // http://localhost:8080/medicos?size=10&page=0&sort=nombre
        // return medicoRepository.findAll(paginacion).map(ListMedicoDto::new);
        return medicoRepository.findByActivoTrue(paginacion).map(ListMedicoDto::new);
        /*
        *    return medicoRepository.findAll().stream()
        *        .map(ListMedicoDto::new)
        *        .collect(Collectors.toList());
        */
    }

    @PutMapping("/{id}")
    @Transactional
    public void update(@PathVariable Long id, @RequestBody @Valid UpdateMedicoDto updateMedicoDto) {
        Medico medico = medicoRepository.getReferenceById(id);
        medico.update(updateMedicoDto);
        medicoRepository.save(medico);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable Long id) {
        // medicoRepository.deleteById(id); // Fisical delete
        // Logical delete
        Medico medico = medicoRepository.getReferenceById(id);
        medico.logicalDelete();
    }
}
