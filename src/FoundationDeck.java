public class FoundationDeck {
    private Palo palo;
    private ListaSimple<Carta> cartas;

    public FoundationDeck(Palo palo) {
        this.palo = palo;
        this.cartas = new ListaSimple<>();
    }

    public boolean estaVacio() {
        return cartas.estaVacia();
    }

    public Carta verUltimaCarta() {
        return cartas.verFin();
    }

    public Carta removerUltimaCarta() {
        if (cartas.estaVacia()) {
            return null;
        }
        return cartas.eliminaFin();
    }

    public boolean sePuedeAgregarCarta(Carta carta) {
        if (carta == null || !carta.tieneElMismoPalo(palo)) {
            return false;
        }
        if (cartas.estaVacia()) {
            return carta.getValorBajo() == 1;
        }
        Carta ultima = cartas.verFin();
        return ultima.getValorBajo() + 1 == carta.getValorBajo();
    }

    public boolean agregarCarta(Carta carta) {
        if (sePuedeAgregarCarta(carta)) {
            cartas.insertaFin(carta);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        if (cartas.estaVacia()) {
            return "[" + palo.getFigura() + ": ---]";
        }
        return "[" + palo.getFigura() + ": " + cartas.verFin() + "]";
    }
}
