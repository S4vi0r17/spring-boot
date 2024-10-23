package med.voll.api.paciente.dto;

import med.voll.api.medico.Medico;

public record ListMedicoDto(
        String nombre,
        String especialidad,
        String documento,
        String email

        public ListMedicoDto(Medico medico) {
            this(medico.nombre(), medico.especialidad().name(), medico.documento(), medico.email());
        }
) { }
