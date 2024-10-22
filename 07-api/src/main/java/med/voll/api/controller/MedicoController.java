package med.voll.api.controller;

import med.voll.api.medico.CreateMedicoDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicos")
public class MedicoController {
    @GetMapping
    public String hello() {
        return "Hello, world!";
    }

    @PostMapping
    public void create(@RequestBody CreateMedicoDto createMedicoDto) {
        System.out.println("Medico: " + createMedicoDto.nombre());
    }
}
