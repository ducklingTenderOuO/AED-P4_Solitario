import java.util.Random;

public class Mazo {
    private ListaCircularDoble<Carta> cartas;

    public Mazo() {
        cartas = new ListaCircularDoble<>();
        llenar();
        mezclar();
    }

    private void llenar() {
        for (Palo palo : Palo.values()) {
            cartas.insertaFin(new Carta(14, palo, palo.getColor()));
            for (int valor = 2; valor <= 13; valor++) {
                cartas.insertaFin(new Carta(valor, palo, palo.getColor()));
            }
        }
    }

    public void mezclar() {
        ListaCircularDoble<Carta> mazoMezclado = new ListaCircularDoble<>();
        Random random = new Random();
        int restantes = cartas.getTamano();

        while (restantes > 0) {
            int posAleatoria = random.nextInt(restantes);
            Carta extraida = cartas.eliminaEnPosicion(posAleatoria);
            mazoMezclado.insertaFin(extraida);
            restantes--;
        }
        cartas = mazoMezclado;
    }

    /**
     * Ordenar compareTo
     */
    public void ordenar() {
        if (cartas.estaVacia()) {
            return;
        }
        NodoDoble<Carta> actual = cartas.getInicio();
        do {
            NodoDoble<Carta> siguiente = actual.getSig();
            while (siguiente != cartas.getInicio()) {
                if (actual.getInfo().compareTo(siguiente.getInfo()) > 0) {
                    Carta temp = actual.getInfo();
                    actual.setInfo(siguiente.getInfo());
                    siguiente.setInfo(temp);
                }
                siguiente = siguiente.getSig();
            }
            actual = actual.getSig();
        } while (actual != cartas.getInicio());
    }

    public Carta obtenerUnaCarta() {
        if (!cartas.estaVacia()) {
            return cartas.eliminaInicio();
        }
        return null;
    }

    public int getTamano() {
        return cartas.getTamano();
    }

    @Override
    public String toString() {
        if (cartas.estaVacia()) {
            return "Mazo vacío";
        }
        StringBuilder sb = new StringBuilder();
        NodoDoble<Carta> r = cartas.getInicio();
        int contador = 0;
        do {
            sb.append(String.format("%-5s", r.getInfo().toString()));
            contador++;
            if (contador % 13 == 0) {
                sb.append("\n");
            }
            r = r.getSig();
        } while (r != cartas.getInicio());
        return sb.toString();
    }
}
