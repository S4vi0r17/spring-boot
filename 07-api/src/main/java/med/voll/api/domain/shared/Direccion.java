package med.voll.api.domain.shared;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    public Direccion update(DireccionDto direccion) {
        this.calle = direccion.calle();
        this.distrito = direccion.distrito();
        this.ciudad = direccion.ciudad();
        this.numero = direccion.numero();
        this.complemento = direccion.complemento();
        return this;
    }
}
