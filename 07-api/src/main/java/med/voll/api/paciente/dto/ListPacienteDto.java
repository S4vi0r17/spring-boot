package med.voll.api.paciente.dto;

import med.voll.api.paciente.Paciente;

public record ListPacienteDto(
        String nombre,
        String email,
        String documentoIdentidad,
        String telefono
) {
    public ListPacienteDto(Paciente paciente) {
        this(paciente.getNombre(), paciente.getEmail(), paciente.getDocumentoIdentidad(), paciente.getTelefono());
    }
}
