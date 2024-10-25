package med.voll.api.paciente.dto;

public record PacienteResponse(
        Long id,
        String nombre,
        String documento,
        String email
) { }
