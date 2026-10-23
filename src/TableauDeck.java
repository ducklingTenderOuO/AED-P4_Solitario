public class TableauDeck {
    private ListaSimple<Carta> cartas;

    public TableauDeck() {
        this.cartas = new ListaSimple<>();
    }

    public void agregarCartaInicial(Carta carta) {
        cartas.insertaFin(carta);
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

    /**
     * Si la columna esta vacia solo se acepta un Rey (valor == 13)
     * Si no esta vacia debe ser del mismo palo y un rango menor a la ultima carta
     */
    public boolean sePuedeAgregarCarta(Carta carta) {
        if (carta == null) {
            return false;
        }
        if (cartas.estaVacia()) {
            return carta.getValor() == 13;
        }
        Carta ultima = cartas.verFin();
        return ultima.esSecuenciaDescendenteMismoPalo(carta);
    }

    public boolean agregarCarta(Carta carta) {
        if (sePuedeAgregarCarta(carta)) {
            cartas.insertaFin(carta);
            return true;
        }
        return false;
    }

    /**
     * secuencia descendente del mismo palo
     * Devuelve 0 si no existe ningun bloque valido que embone con el destino
     */
    public boolean sePuedeMoverBloqueHacia(TableauDeck destino, int cantidad, int maxCartasPermitidas) {
        int total = cartas.getTamano();
        if (cantidad <= 0 || cantidad > total || cantidad > maxCartasPermitidas) {
            return false;
        }

        int indiceInicio = total - cantidad;
        for (int i = indiceInicio; i < total - 1; i++) {
            Carta actual = cartas.obtenerEnPosicion(i);
            Carta siguiente = cartas.obtenerEnPosicion(i + 1);
            if (!actual.esSecuenciaDescendenteMismoPalo(siguiente)) {
                return false;
            }
        }

        Carta primeraDelBloque = cartas.obtenerEnPosicion(indiceInicio);
        return destino.sePuedeAgregarCarta(primeraDelBloque);
    }

    /**
     * Extrae las últimas 'cantidad' cartas de esta columna conservando su orden original.
     */
    public ListaSimple<Carta> extraerBloqueFinal(int cantidad) {
        ListaSimple<Carta> bloque = new ListaSimple<>();
        for (int i = 0; i < cantidad; i++) {
            Carta extraida = cartas.eliminaFin();
            if (extraida != null) {
                bloque.insertaInicio(extraida);
            }
        }
        return bloque;
    }

    /**
     * Agrega al final de esta columna todas las cartas de un bloque previamente validado.
     */
    public void agregarBloque(ListaSimple<Carta> bloque) {
        Nodo<Carta> r = bloque.getInicio();
        while (r != null) {
            cartas.insertaFin(r.getInfo());
            r = r.getSig();
        }
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
