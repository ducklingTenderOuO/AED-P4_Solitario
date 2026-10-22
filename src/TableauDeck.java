public class TableauDeck {
    private ListaSimple<Carta> cartas;

    public TableauDeck() {
        this.cartas = new ListaSimple<>();
    }

    public void agregarCartaInicial(Carta carta) {
        cartas.insertaFin(carta);
    }

    @Override
    public String toString() {
        if (cartas.estaVacia()) {
            return "---";
        }
        StringBuilder sb = new StringBuilder();
        Nodo<Carta> r = cartas.getInicio();
        while (r != null) {
            sb.append(String.format("%-6s", r.getInfo().toString()));
            r = r.getSig();
        }
        return sb.toString();
    }
}
