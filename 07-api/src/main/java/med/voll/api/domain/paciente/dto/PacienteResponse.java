package med.voll.api.domain.paciente.dto;

public record PacienteResponse(
        Long id,
        String nombre,
        String documento,
        String email
) { }
