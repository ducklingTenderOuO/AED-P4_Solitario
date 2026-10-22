/**
 * Clase Solitario (Eight Off) que inicializa el mazo, reparte las cartas
 * en las estructuras de ListaSimple e imprime el tablero en formato consola.
 */
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

    /**
     * Crea las 4 fundaciones (una para cada palo).
     */
    private void inicializarFoundations() {
        for (Palo palo : Palo.values()) {
            foundations.insertaFin(new FoundationDeck(palo));
        }
    }

    /**
     * Crea las 8 columnas del tablero (Tableaux) y reparte 6 cartas en cada una (48 cartas).
     */
    private void inicializarTableaux() {
        for (int i = 0; i < 8; i++) {
            TableauDeck columna = new TableauDeck();
            for (int j = 0; j < 6; j++) {
                columna.agregarCartaInicial(mazo.obtenerUnaCarta());
            }
            tableaux.insertaFin(columna);
        }
    }

    /**
     * Crea las 8 celdas libres (Free Cells); coloca las 4 cartas restantes del mazo
     * en las primeras 4 celdas y deja las otras 4 vacías.
     */
    private void inicializarCeldasLibres() {
        for (int i = 0; i < 8; i++) {
            CeldaLibre celda = new CeldaLibre();
            if (i < 4) {
                celda.colocarCarta(mazo.obtenerUnaCarta());
            }
            celdasLibres.insertaFin(celda);
        }
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
        sb.append("EIGHT OFF (Celdas Libres 1-8):\n  ");
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

    public static void main(String[] args) {
        Solitario juego = new Solitario();
        System.out.println(juego);
    }
}
