package med.voll.api.domain.medico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.medico.dto.CreateMedicoDto;
import med.voll.api.domain.medico.dto.Especialidad;
import med.voll.api.domain.medico.dto.UpdateMedicoDto;
import med.voll.api.domain.shared.Direccion;

@Entity
@Table(name = "medicos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String email;

    // @JsonIgnore // Uncomment this line to hide the phone number from the response
    private String telefono;
    private String documento;
    private boolean activo;

    @Enumerated(EnumType.STRING)
    private Especialidad especialidad;

    @Embedded
    private Direccion direccion;

    public Medico(CreateMedicoDto createMedicoDto) {
        this.nombre = createMedicoDto.nombre();
        this.email = createMedicoDto.email();
        this.telefono = createMedicoDto.telefono();
        this.documento = createMedicoDto.documento();
        this.activo = true;
        this.especialidad = createMedicoDto.especialidad();
        this.direccion = new Direccion(createMedicoDto.direccion());
    }

    public void update(UpdateMedicoDto updateMedicoDto) {
        if (updateMedicoDto.nombre() != null) {
            this.nombre = updateMedicoDto.nombre();
        }
        if (updateMedicoDto.documento() != null) {
            this.email = updateMedicoDto.documento();
        }
        if (updateMedicoDto.direccion() != null) {
            this.direccion.update(updateMedicoDto.direccion());
        }
    }

    public void logicalDelete() {
        this.activo = false;
    }
}
