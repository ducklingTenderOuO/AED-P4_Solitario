public class Movimiento {
    public enum TipoMovimiento {
        TABLEAU_A_FOUNDATION,
        TABLEAU_A_CELDA,
        TABLEAU_A_TABLEAU,
        CELDA_A_FOUNDATION,
        CELDA_A_TABLEAU
    }

    private final TipoMovimiento tipo;
    private final int origen;
    private final int destino;
    private final int cantidadCartas;

    public Movimiento(TipoMovimiento tipo, int origen, int destino, int cantidadCartas) {
        this.tipo = tipo;
        this.origen = origen;
        this.destino = destino;
        this.cantidadCartas = cantidadCartas;
    }

    public TipoMovimiento getTipo() {
        return tipo;
    }

    public int getOrigen() {
        return origen;
    }

    public int getDestino() {
        return destino;
    }

    public int getCantidadCartas() {
        return cantidadCartas;
    }
}
