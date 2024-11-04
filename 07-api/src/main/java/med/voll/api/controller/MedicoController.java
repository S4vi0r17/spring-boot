package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.medico.dto.CreateMedicoDto;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.dto.ListMedicoDto;
import med.voll.api.domain.medico.dto.UpdateMedicoDto;
import med.voll.api.domain.medico.dto.MedicoResponse;
import med.voll.api.domain.shared.DireccionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping
    public ResponseEntity<MedicoResponse> create(
            @RequestBody @Valid CreateMedicoDto createMedicoDto,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        Medico medico = medicoRepository.save(new Medico(createMedicoDto));
        MedicoResponse medicoResponse = new MedicoResponse(
                medico.getId(),
                medico.getNombre(),
                medico.getDocumento(),
                medico.getEmail(),
                new DireccionDto(
                        medico.getDireccion().getCalle(),
                        medico.getDireccion().getDistrito(),
                        medico.getDireccion().getCiudad(),
                        medico.getDireccion().getNumero(),
                        medico.getDireccion().getComplemento()
                )
        );

        return ResponseEntity.created(
                uriComponentsBuilder.path("/medicos/{id}")
                        .buildAndExpand(medico.getId())
                        .toUri()
        ).body(medicoResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicoResponse> get(@PathVariable Long id) {
        Medico medico = medicoRepository.getReferenceById(id);
        MedicoResponse medicoResponse = new MedicoResponse(
                medico.getId(),
                medico.getNombre(),
                medico.getDocumento(),
                medico.getEmail(),
                new DireccionDto(
                        medico.getDireccion().getCalle(),
                        medico.getDireccion().getDistrito(),
                        medico.getDireccion().getCiudad(),
                        medico.getDireccion().getNumero(),
                        medico.getDireccion().getComplemento()
                )
        );

        return ResponseEntity.ok(medicoResponse);
    }

    @GetMapping
    public ResponseEntity<Page<ListMedicoDto>> list(@PageableDefault(size = 2) Pageable paginacion) {
        // http://localhost:8080/medicos?size=10&page=0&sort=nombre
        // return medicoRepository.findAll(paginacion).map(ListMedicoDto::new);
        return ResponseEntity.ok(
                medicoRepository.findAll(paginacion).map(ListMedicoDto::new)
        );
        /*
        *    return medicoRepository.findAll().stream()
        *        .map(ListMedicoDto::new)
        *        .collect(Collectors.toList());
        */
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<MedicoResponse> update(@PathVariable Long id, @RequestBody @Valid UpdateMedicoDto updateMedicoDto) {
        Medico medico = medicoRepository.getReferenceById(id);
        medico.update(updateMedicoDto);
        medicoRepository.save(medico);
        return ResponseEntity.ok(
                new MedicoResponse(
                        medico.getId(),
                        medico.getNombre(),
                        medico.getDocumento(),
                        medico.getEmail(),
                        new DireccionDto(
                                medico.getDireccion().getCalle(),
                                medico.getDireccion().getDistrito(),
                                medico.getDireccion().getCiudad(),
                                medico.getDireccion().getNumero(),
                                medico.getDireccion().getComplemento()
                        )
                )
        );
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable Long id) {
        // medicoRepository.deleteById(id); // Fisical delete
        // Logical delete
        Medico medico = medicoRepository.getReferenceById(id);
        medico.logicalDelete();
    }

//    @GetMapping("detail/{id}")
//    @Secured("ROLE_ADMIN")
//    public ResponseEntity<?> detail(@PathVariable Long id) {
//        var medico = medicoRepository.getReferenceById(id);
//        return ResponseEntity.ok(new MedicoResponse(
//                medico.getId(),
//                medico.getNombre(),
//                medico.getDocumento(),
//                medico.getEmail(),
//                new DireccionDto(
//                        medico.getDireccion().getCalle(),
//                        medico.getDireccion().getDistrito(),
//                        medico.getDireccion().getCiudad(),
//                        medico.getDireccion().getNumero(),
//                        medico.getDireccion().getComplemento()
//                )
//        ));
//    }
}
