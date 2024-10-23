package med.voll.api.medico.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.medico.direccion.Direccion;
import med.voll.api.medico.dto.CreateMedicoDto;
import med.voll.api.medico.dto.Especialidad;

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
    private String documento;

    @Enumerated(EnumType.STRING)
    private Especialidad especialidad;

    @Embedded
    private Direccion direccion;

    public Medico(CreateMedicoDto createMedicoDto) {
        this.nombre = createMedicoDto.nombre();
        this.email = createMedicoDto.email();
        this.documento = createMedicoDto.documento();
        this.especialidad = createMedicoDto.especialidad();
        this.direccion = new Direccion(createMedicoDto.direccion());
    }
}
