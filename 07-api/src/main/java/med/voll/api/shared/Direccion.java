package med.voll.api.medico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.direccion.DireccionDto;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Direccion {
    private String calle;
    private String distrito;
    private String ciudad;
    private String numero;
    private String complemento;

    public Direccion(DireccionDto direccionDto) {
        this.calle = direccionDto.calle();
        this.distrito = direccionDto.distrito();
        this.ciudad = direccionDto.ciudad();
        this.numero = direccionDto.numero();
        this.complemento = direccionDto.complemento();
    }
}
