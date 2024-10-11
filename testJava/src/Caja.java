public class Caja<T> {
    private T contenido;

    public T getContenido() {
        return contenido;
    }

    public void setContenido(T contenido) {
        this.contenido = contenido;
    }

    public <t> t sumaContenidoEnCaja(t valor) {
        if (this.contenido instanceof Integer c && valor instanceof Integer i) {
            Integer resultado = c + i;
            return (t) resultado;
        } else if (this.contenido instanceof Double c && valor instanceof Double d) {
            Double resultado = c + d;
            return (t) resultado;
        } else if (this.contenido instanceof String c && valor instanceof String s) {
            String resultado = c + "\n" + s;
            return (t) resultado;
        }

        return null;
    }
}
