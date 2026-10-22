public class Solitario {
    private Mazo mazo;

    public Solitario() {
        mazo = new Mazo();
    }

    public void imprimirMazo() {
        System.out.println("=== MAZO MEZCLADO (" + mazo.getTamano() + " cartas en ListaCircularDoble) ===");
        System.out.println(mazo);

        mazo.ordenar();
        System.out.println("=== MAZO ORDENADO CON COMPARABLE (Rango y Palo) ===");
        System.out.println(mazo);
    }

    public static void main(String[] args) {
        Solitario solitario = new Solitario();
        solitario.imprimirMazo();
    }
}
