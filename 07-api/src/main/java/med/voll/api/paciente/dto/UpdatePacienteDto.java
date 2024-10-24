package med.voll.api.paciente.dto;

import jakarta.validation.constraints.NotNull;
import med.voll.api.shared.DireccionDto;

public record UpdatePacienteDto(
        @NotNull
        Long id,
        String nombre,
        String email,
        String telefono
) { }
