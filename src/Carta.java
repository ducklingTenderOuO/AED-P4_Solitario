/**
 * Representa una carta de la baraja inglesa.
 * Implementa Comparable para identificar qué carta es mayor
 * de acuerdo a los rangos e importando el palo de la carta.
 */
public class Carta implements Comparable<Carta> {
    private int valor;
    private Palo palo;
    private String color;
    private int valorBajo;

    public Carta(int valor, Palo palo, String color) {
        this.valor = valor;
        this.palo = palo;
        this.color = color;
        if (valor == 14) {
            this.valorBajo = 1; // El As vale 1 en las escaleras de Solitario / Eight Off
        } else {
            this.valorBajo = valor;
        }
    }

    public int getValor() {
        return valor;
    }

    public int getValorBajo() {
        return valorBajo;
    }

    public Palo getPalo() {
        return palo;
    }

    public String getColor() {
        return color;
    }

    /**
     * Compara dos cartas por su rango (valor) y, en caso de empate,
     * por el peso de su palo (Trébol < Diamante < Corazón < Pica).
     */
    @Override
    public int compareTo(Carta o) {
        if (this.valorBajo == o.valorBajo) {
            return this.palo.getPeso() - o.palo.getPeso();
        }
        return this.valorBajo - o.valorBajo;
    }

    @Override
    public String toString() {
        return switch (valor) {
            case 14 -> "A" + palo.getFigura();
            case 11 -> "J" + palo.getFigura();
            case 12 -> "Q" + palo.getFigura();
            case 13 -> "K" + palo.getFigura();
            case 15 -> "Joker";
            default -> valor + palo.getFigura();
        };
    }
}
