public class FoundationDeck {
    private Palo palo;
    private ListaSimple<Carta> cartas;

    public FoundationDeck(Palo palo) {
        this.palo = palo;
        this.cartas = new ListaSimple<>();
    }

    @Override
    public String toString() {
        if (cartas.estaVacia()) {
            return "[" + palo.getFigura() + ": ---]";
        }
        return "[" + palo.getFigura() + ": " + cartas.verFin() + "]";
    }
}
