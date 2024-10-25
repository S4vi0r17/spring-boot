package med.voll.api.medico.dto;

import med.voll.api.medico.Medico;

public record ListMedicoDto(
        Long id,
        String nombre,
        String especialidad,
        String documento,
        String email
) {
    public ListMedicoDto(Medico medico) {
        this(medico.getId(), medico.getNombre(), String.valueOf(medico.getEspecialidad()), medico.getDocumento(), medico.getEmail());
    }
}
