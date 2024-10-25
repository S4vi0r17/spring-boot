package med.voll.api.domain.paciente.dto;

import jakarta.validation.constraints.NotNull;

public record UpdatePacienteDto(
        @NotNull
        Long id,
        String nombre,
        String email,
        String telefono
) { }
