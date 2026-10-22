public class CeldaLibre {
    private Carta carta;

    public CeldaLibre() {
        this.carta = null;
    }

    public boolean estaVacia() {
        return carta == null;
    }

    public Carta verCarta() {
        return carta;
    }

    public boolean colocarCarta(Carta nuevaCarta) {
        if (estaVacia() && nuevaCarta != null) {
            this.carta = nuevaCarta;
            return true;
        }
        return false;
    }

    public Carta retirarCarta() {
        Carta temporal = this.carta;
        this.carta = null;
        return temporal;
    }

    @Override
    public String toString() {
        if (estaVacia()) {
            return "[ --- ]";
        }
        return "[ " + String.format("%-3s", carta.toString()) + " ]";
    }
}
