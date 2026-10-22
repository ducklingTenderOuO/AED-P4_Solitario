public class ListaSimple<T> {
    private Nodo<T> inicio;

    public ListaSimple() {
        this.inicio = null;
    }

    public boolean estaVacia() {
        return inicio == null;
    }

    public Nodo<T> getInicio() {
        return inicio;
    }

    public T verFin() {
        if (inicio == null) {
            return null;
        }
        Nodo<T> r = inicio;
        while (r.getSig() != null) {
            r = r.getSig();
        }
        return r.getInfo();
    }

    public void insertaInicio(T dato) {
        Nodo<T> n = new Nodo<>(dato);
        n.setSig(inicio);
        inicio = n;
    }

    public void insertaFin(T dato) {
        Nodo<T> n = new Nodo<>(dato);
        if (inicio == null) {
            n.setSig(inicio);
            inicio = n;
        } else {
            Nodo<T> r = inicio;
            while (r.getSig() != null) {
                r = r.getSig();
            }
            r.setSig(n);
            n.setSig(null);
        }
    }

    public T eliminaInicio() {
        T datoEliminado = null;
        if (inicio == null) {
            System.out.println("Lista vacia");
        } else {
            datoEliminado = inicio.getInfo();
            inicio = inicio.getSig();
        }
        return datoEliminado;
    }

    public T eliminaFin() {
        T datoEliminado = null;
        if (inicio == null) {
            System.out.println("Lista vacia");
        } else {
            if (inicio.getSig() == null) {
                datoEliminado = inicio.getInfo();
                inicio = null;
            } else {
                Nodo<T> r = inicio;
                Nodo<T> a = r;
                while (r.getSig() != null) {
                    a = r;
                    r = r.getSig();
                }
                datoEliminado = r.getInfo();
                a.setSig(null);
            }
        }
        return datoEliminado;
    }

    public String recorrer() {
        String cadena = "";
        if (inicio != null) {
            Nodo<T> r = inicio;
            while (r != null) {
                cadena += r.getInfo();
                r = r.getSig();
            }
        } else {
            cadena = "Lista vacia";
        }
        return cadena;
    }
}
