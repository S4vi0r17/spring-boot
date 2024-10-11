public class TesteaCaja {
    public static void main(String[] args) {
        Caja<String> cajaDeTexto = new Caja();
        cajaDeTexto.setContenido("Guardando texto en mi caja!");
        System.out.println(cajaDeTexto.sumaContenidoEnCaja("Otra línea"));

        Caja<Integer> cajaDeEdad = new Caja();
        cajaDeEdad.setContenido(30);
        System.out.println(cajaDeEdad.sumaContenidoEnCaja(26));

        Caja<Double> cajaDeValor = new Caja<>();
        cajaDeValor.setContenido(150.50);
        System.out.println(cajaDeValor.sumaContenidoEnCaja(350.50));
        System.out.println(cajaDeValor.sumaContenidoEnCaja("texto"));
    }
}
