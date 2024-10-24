package med.voll.api.medico.dto;

import jakarta.validation.constraints.NotNull;
import med.voll.api.shared.DireccionDto;

public record UpdateMedicoDto(
//        @NotNull
//        Long id,
        String nombre,
        String documento,
        DireccionDto direccion
) { }
