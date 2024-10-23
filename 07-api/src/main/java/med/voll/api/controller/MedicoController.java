package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.medico.MedicoRepository;
import med.voll.api.medico.dto.CreateMedicoDto;
import med.voll.api.medico.model.Medico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping
    public void create(@RequestBody @Valid CreateMedicoDto createMedicoDto) {
        medicoRepository.save(new Medico(createMedicoDto));
    }
}
