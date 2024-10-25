package med.voll.api.domain.medico.dto;

import med.voll.api.domain.shared.DireccionDto;

public record MedicoResponse(
        Long id,
        String nombre,
        String documento,
        String email,
        DireccionDto direccion
) { }
