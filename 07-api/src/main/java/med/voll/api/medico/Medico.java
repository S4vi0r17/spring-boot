package med.voll.api.medico;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.medico.dto.CreateMedicoDto;
import med.voll.api.medico.dto.Especialidad;
import med.voll.api.shared.Direccion;

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

    @Enumerated(EnumType.STRING)
    private Especialidad especialidad;

    @Embedded
    private Direccion direccion;

    public Medico(CreateMedicoDto createMedicoDto) {
        this.nombre = createMedicoDto.nombre();
        this.email = createMedicoDto.email();
        this.telefono = createMedicoDto.telefono();
        this.documento = createMedicoDto.documento();
        this.especialidad = createMedicoDto.especialidad();
        this.direccion = new Direccion(createMedicoDto.direccion());
    }
}
