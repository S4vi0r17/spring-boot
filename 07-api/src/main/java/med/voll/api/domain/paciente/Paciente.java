package med.voll.api.domain.paciente;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.paciente.dto.UpdatePacienteDto;
import med.voll.api.domain.shared.Direccion;
import med.voll.api.domain.paciente.dto.CreatePacienteDto;

@Getter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Paciente")
@Table(name = "pacientes")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String email;
    private String documentoIdentidad;
    private String telefono;
    private boolean activo;

    @Embedded
    private Direccion direccion;

    public Paciente(CreatePacienteDto datos) {
        this.nombre = datos.nombre();
        this.email = datos.email();
        this.telefono = datos.telefono();
        this.documentoIdentidad = datos.documentoIdentidad();
        this.direccion = new Direccion(datos.direccion());
    }

    public void update(UpdatePacienteDto updatePacienteDto) {
        if (updatePacienteDto.nombre() != null) {
            this.nombre = updatePacienteDto.nombre();
        }
        if (updatePacienteDto.email() != null) {
            this.email = updatePacienteDto.email();
        }
        if (updatePacienteDto.telefono() != null) {
            this.telefono = updatePacienteDto.telefono();
        }
    }
}
