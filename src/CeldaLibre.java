public class CeldaLibre {
    private Carta carta;

    public CeldaLibre() {
        this.carta = null;
    }

    public void colocarCarta(Carta carta) {
        this.carta = carta;
    }

    public boolean estaVacia() {
        return carta == null;
    }

    @Override
    public String toString() {
        if (estaVacia()) {
            return "[ --- ]";
        }
        return "[ " + String.format("%-3s", carta.toString()) + " ]";
    }
}
