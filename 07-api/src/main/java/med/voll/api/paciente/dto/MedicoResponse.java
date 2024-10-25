package med.voll.api.paciente.dto;

import med.voll.api.shared.DireccionDto;

public record MedicoResponse(
        Long id,
        String nombre,
        String documento,
        String email,
        DireccionDto direccion
) { }
