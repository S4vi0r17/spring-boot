package med.voll.api.medico.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.direccion.DireccionDto;

public record CreateMedicoDto(
        @NotBlank
        String nombre,

        @NotBlank
        @Email
        String email,

        @NotBlank
        @Pattern(regexp = "\\d{8}")
        String documento,

        @NotNull
        Especialidad especialidad,

        @NotNull
        @Valid
        DireccionDto direccion
) { }
