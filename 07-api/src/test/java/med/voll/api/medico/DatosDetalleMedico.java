package med.voll.api.medico;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.medico.dto.Especialidad;
import med.voll.api.domain.shared.Direccion;

public record DatosDetalleMedico(
        Object o,
        @NotBlank String nombre,
        @NotBlank @Email String email,
        @NotBlank @Pattern(regexp = "\\d{8}") String documento,
        @NotBlank String telefono,
        @NotNull Especialidad especialidad,
        Direccion direccion
) { }
