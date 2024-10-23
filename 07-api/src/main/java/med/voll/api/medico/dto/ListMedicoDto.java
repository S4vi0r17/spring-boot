package med.voll.api.medico.dto;

import med.voll.api.medico.Medico;

public record ListMedicoDto(
        String nombre,
        String especialidad,
        String documento,
        String email
) {
    public ListMedicoDto(Medico medico) {
        this(medico.getNombre(), String.valueOf(medico.getEspecialidad()), medico.getDocumento(), medico.getEmail());
    }
}
