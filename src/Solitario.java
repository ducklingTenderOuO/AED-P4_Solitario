public class Solitario {
    private Mazo mazo;
    private ListaSimple<FoundationDeck> foundations;
    private ListaSimple<CeldaLibre> celdasLibres;
    private ListaSimple<TableauDeck> tableaux;

    public Solitario() {
        mazo = new Mazo();
        foundations = new ListaSimple<>();
        celdasLibres = new ListaSimple<>();
        tableaux = new ListaSimple<>();

        inicializarFoundations();
        inicializarTableaux();
        inicializarCeldasLibres();
    }

    private void inicializarFoundations() {
        for (Palo palo : Palo.values()) {
            foundations.insertaFin(new FoundationDeck(palo));
        }
    }

    private void inicializarTableaux() {
        for (int i = 0; i < 8; i++) {
            TableauDeck columna = new TableauDeck();
            for (int j = 0; j < 6; j++) {
                columna.agregarCartaInicial(mazo.obtenerUnaCarta());
            }
            tableaux.insertaFin(columna);
        }
    }

    private void inicializarCeldasLibres() {
        for (int i = 0; i < 8; i++) {
            CeldaLibre celda = new CeldaLibre();
            if (i < 4) {
                celda.colocarCarta(mazo.obtenerUnaCarta());
            }
            celdasLibres.insertaFin(celda);
        }
    }

    /**
     * cuantas de las 8 celdas libres de arriba están vacias?
     */
    public int contarCeldasLibresVacias() {
        int vacias = 0;
        Nodo<CeldaLibre> r = celdasLibres.getInicio();
        while (r != null) {
            if (r.getInfo().estaVacia()) {
                vacias++;
            }
            r = r.getSig();
        }
        return vacias;
    }

    /**
     * Mueve la ultima carta de una columna (1-8) a su fundacion
     */
    public boolean moverTableauAFoundation(int numColumna) {
        if (numColumna < 1 || numColumna > 8) {
            return false;
        }
        TableauDeck origen = tableaux.obtenerEnPosicion(numColumna - 1);
        if (origen == null || origen.estaVacio()) {
            return false;
        }
        Carta carta = origen.verUltimaCarta();
        FoundationDeck destino = foundations.obtenerEnPosicion(carta.getPalo().ordinal());
        if (destino != null && destino.agregarCarta(carta)) {
            origen.removerUltimaCarta();
            return true;
        }
        return false;
    }

    /**
     * Mueve la carta de una celda libre (1-8) a su fundacion
     */
    public boolean moverCeldaLibreAFoundation(int numCelda) {
        if (numCelda < 1 || numCelda > 8) {
            return false;
        }
        CeldaLibre origen = celdasLibres.obtenerEnPosicion(numCelda - 1);
        if (origen == null || origen.estaVacia()) {
            return false;
        }
        Carta carta = origen.verCarta();
        FoundationDeck destino = foundations.obtenerEnPosicion(carta.getPalo().ordinal());
        if (destino != null && destino.agregarCarta(carta)) {
            origen.retirarCarta();
            return true;
        }
        return false;
    }

    /**
     * Mueve la ultima carta de una columna (1-8) a una celda libre vacia (1-8)
     */
    public boolean moverTableauACeldaLibre(int numColumna, int numCelda) {
        if (numColumna < 1 || numColumna > 8 || numCelda < 1 || numCelda > 8) {
            return false;
        }
        TableauDeck origen = tableaux.obtenerEnPosicion(numColumna - 1);
        CeldaLibre destino = celdasLibres.obtenerEnPosicion(numCelda - 1);
        if (origen == null || origen.estaVacio() || destino == null || !destino.estaVacia()) {
            return false;
        }
        Carta carta = origen.removerUltimaCarta();
        destino.colocarCarta(carta);
        return true;
    }

    /**
     * Mueve la carta de una celda libre (1-8) al final de una columna (1-8)
     * */
    public boolean moverCeldaLibreATableau(int numCelda, int numColumna) {
        if (numCelda < 1 || numCelda > 8 || numColumna < 1 || numColumna > 8) {
            return false;
        }
        CeldaLibre origen = celdasLibres.obtenerEnPosicion(numCelda - 1);
        TableauDeck destino = tableaux.obtenerEnPosicion(numColumna - 1);
        if (origen == null || origen.estaVacia() || destino == null) {
            return false;
        }
        Carta carta = origen.verCarta();
        if (destino.agregarCarta(carta)) {
            origen.retirarCarta();
            return true;
        }
        return false;
    }

    /**
     * Mueve una carta o un bloque de cartas en secuencia descendente del mismo palo
     * de una columna origen (1-8) a una columna destino (1-8).
     * El tamaño máximo del bloque depende de (1 + celdas libres vacías).
     */
    public boolean moverTableauATableau(int colOrigen, int colDestino) {
        if (colOrigen < 1 || colOrigen > 8 || colDestino < 1 || colDestino > 8 || colOrigen == colDestino) {
            return false;
        }
        TableauDeck origen = tableaux.obtenerEnPosicion(colOrigen - 1);
        TableauDeck destino = tableaux.obtenerEnPosicion(colDestino - 1);
        if (origen == null || destino == null || origen.estaVacio()) {
            return false;
        }

        int maxCartas = 1 + contarCeldasLibresVacias();
        int cantidadAMover = origen.calcularCantidadMovibleHacia(destino, maxCartas);
        if (cantidadAMover > 0) {
            ListaSimple<Carta> bloque = origen.extraerBloqueFinal(cantidadAMover);
            destino.agregarBloque(bloque);
            return true;
        }
        return false;
    }

    /**
     * Determina si el jugador ha ganado el juego (las 4 fundaciones tienen el Rey = 13).
     */
    public boolean isGameOver() {
        Nodo<FoundationDeck> r = foundations.getInicio();
        while (r != null) {
            FoundationDeck base = r.getInfo();
            if (base.estaVacio() || base.verUltimaCarta().getValor() != 13) {
                return false;
            }
            r = r.getSig();
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("==================================================================\n");
        sb.append("                      SOLITARIO: EIGHT OFF                        \n");
        sb.append("==================================================================\n");

        // Imprimir Fundaciones
        sb.append("FOUNDATIONS (Bases):\n  ");
        Nodo<FoundationDeck> nodoFound = foundations.getInicio();
        while (nodoFound != null) {
            sb.append(nodoFound.getInfo()).append("   ");
            nodoFound = nodoFound.getSig();
        }
        sb.append("\n\n");

        // Imprimir las 8 Celdas Libres (Reservas)
        sb.append("EIGHT OFF (Celdas Libres 1-8) [Máx bloque movible: ")
          .append(1 + contarCeldasLibresVacias()).append("]:\n  ");
        Nodo<CeldaLibre> nodoCelda = celdasLibres.getInicio();
        int numCelda = 1;
        while (nodoCelda != null) {
            sb.append(numCelda).append(":").append(nodoCelda.getInfo()).append(" ");
            nodoCelda = nodoCelda.getSig();
            numCelda++;
        }
        sb.append("\n\n");

        // Imprimir las 8 Columnas (Tableaux)
        sb.append("TABLEAUX (Columnas 1-8):\n");
        Nodo<TableauDeck> nodoTab = tableaux.getInicio();
        int numColumna = 1;
        while (nodoTab != null) {
            sb.append("  Columna ").append(numColumna).append(": ").append(nodoTab.getInfo()).append("\n");
            nodoTab = nodoTab.getSig();
            numColumna++;
        }
        sb.append("==================================================================");
        return sb.toString();
    }
}
