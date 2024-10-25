package med.voll.api.domain.paciente.dto;

import med.voll.api.domain.paciente.Paciente;

public record ListPacienteDto(
        Long id,
        String nombre,
        String email,
        String documentoIdentidad,
        String telefono
) {
    public ListPacienteDto(Paciente paciente) {
        this(paciente.getId(), paciente.getNombre(), paciente.getEmail(), paciente.getDocumentoIdentidad(), paciente.getTelefono());
    }
}
