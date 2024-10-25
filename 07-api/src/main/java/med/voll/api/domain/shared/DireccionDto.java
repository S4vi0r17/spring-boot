package med.voll.api.domain.shared;

import jakarta.validation.constraints.NotBlank;

public record DireccionDto(
        @NotBlank
        String calle,

        @NotBlank
        String distrito,

        @NotBlank
        String ciudad,

        @NotBlank
        String numero,

        @NotBlank
        String complemento

) { }
