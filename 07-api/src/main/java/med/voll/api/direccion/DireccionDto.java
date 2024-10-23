package med.voll.api.direccion;

public record Direccion(
        String calle,
        String distrito,
        String ciudad,
        String numero,
        String complemento
) { }
