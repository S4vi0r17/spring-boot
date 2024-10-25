package med.voll.api.domain.medico.dto;

import med.voll.api.domain.shared.DireccionDto;

public record UpdateMedicoDto(
//        @NotNull
//        Long id,
        String nombre,
        String documento,
        DireccionDto direccion
) { }
