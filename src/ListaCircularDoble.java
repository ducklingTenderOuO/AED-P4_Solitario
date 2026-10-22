public class ListaCircularDoble<T> {
    private NodoDoble<T> inicio;
    private NodoDoble<T> fin;

    public ListaCircularDoble() {
        this.inicio = null;
        this.fin = null;
    }

    public boolean estaVacia() {
        return inicio == null;
    }

    public int getTamano() {
        if (inicio == null) {
            return 0;
        }
        int contador = 0;
        NodoDoble<T> r = inicio;
        do {
            contador++;
            r = r.getSig();
        } while (r != inicio);
        return contador;
    }

    public NodoDoble<T> getInicio() {
        return inicio;
    }

    public void insertaInicio(T dato) {
        NodoDoble<T> n = new NodoDoble<>(dato);
        if (inicio == null) {
            inicio = fin = n;
            n.setSig(inicio);
            n.setAnt(inicio);
        } else {
            n.setSig(inicio);
            inicio.setAnt(n);
            inicio = n;
            fin.setSig(inicio);
            n.setAnt(fin);
        }
    }

    public void insertaFin(T dato) {
        NodoDoble<T> n = new NodoDoble<>(dato);
        if (inicio == null) {
            inicio = fin = n;
            n.setSig(inicio);
            n.setAnt(inicio);
        } else {
            n.setSig(inicio);
            inicio.setAnt(n);
            fin.setSig(n);
            n.setAnt(fin);
            fin = n;
        }
    }

    public T eliminaInicio() {
        T datoEliminado = null;
        if (inicio == null) {
            System.out.println("Lista vacia");
        } else {
            datoEliminado = inicio.getInfo();
            if (inicio == fin) {
                inicio = fin = null;
            } else {
                fin.setSig(inicio.getSig());
                inicio = inicio.getSig();
                inicio.setAnt(fin);
            }
        }
        return datoEliminado;
    }

    public T eliminaFin() {
        T datoEliminado = null;
        if (inicio == null) {
            System.out.println("Lista vacia");
        } else {
            datoEliminado = fin.getInfo();
            if (inicio == fin) {
                inicio = fin = null;
            } else {
                NodoDoble<T> r = fin.getAnt();
                r.setSig(inicio);
                inicio.setAnt(r);
                fin = r;
            }
        }
        return datoEliminado;
    }

    public T eliminaEnPosicion(int posicion) {
        if (inicio == null || posicion < 0) {
            return null;
        }
        if (posicion == 0) {
            return eliminaInicio();
        }
        NodoDoble<T> r = inicio;
        for (int i = 0; i < posicion; i++) {
            r = r.getSig();
            if (r == inicio) {
                return null; // fuera de rango
            }
        }
        if (r == fin) {
            return eliminaFin();
        }
        T datoEliminado = r.getInfo();
        r.getAnt().setSig(r.getSig());
        r.getSig().setAnt(r.getAnt());
        return datoEliminado;
    }

    public String recorre() {
        String cadena = "";
        if (inicio != null) {
            NodoDoble<T> r = inicio;
            do {
                cadena += r.getInfo() + " ";
                r = r.getSig();
            } while (r != inicio);
        } else {
            cadena = "Lista vacia";
        }
        return cadena;
    }
}
