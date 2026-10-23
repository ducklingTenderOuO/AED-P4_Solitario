import java.util.Scanner;

public class SolitarioTextUI {
    private Solitario solitario;
    private Scanner sc;

    public SolitarioTextUI() {
        solitario = new Solitario();
        sc = new Scanner(System.in);
    }

    public void playGame() {
        System.out.println(solitario);
        menu();
        System.out.print("¿Qué operación quieres hacer?: ");
        String op = sc.nextLine().trim().toUpperCase();

        while (!op.equals("X") && !solitario.isGameOver() && solitario.hayMovimientosDisponibles()) {
            boolean exito = false;
            switch (op) {
                case "A" -> {
                    int col = pedirNumero("Escribe el número de Columna (1-8) de donde se toma la carta: ");
                    exito = solitario.moverTableauAFoundation(col);
                    mostrarResultado(exito);
                }
                case "B" -> {
                    int col = pedirNumero("Escribe el número de Columna (1-8) de donde se toma la carta: ");
                    int celda = pedirNumero("Escribe el número de Celda Libre (1-8) donde se colocará: ");
                    exito = solitario.moverTableauACeldaLibre(col, celda);
                    mostrarResultado(exito);
                }
                case "C" -> {
                    int colOrigen = pedirNumero("Escribe el número de Columna origen (1-8): ");
                    int colDestino = pedirNumero("Escribe el número de Columna destino (1-8): ");
                    int cantidad = pedirNumero("Escribe la cantidad de cartas a mover (máx " + (1 + solitario.contarCeldasLibresVacias()) + "): ");
                    exito = solitario.moverTableauATableau(colOrigen, colDestino, cantidad);
                    mostrarResultado(exito);
                }
                case "D" -> {
                    int celda = pedirNumero("Escribe el número de Celda Libre (1-8) de donde se toma la carta: ");
                    exito = solitario.moverCeldaLibreAFoundation(celda);
                    mostrarResultado(exito);
                }
                case "E" -> {
                    int celda = pedirNumero("Escribe el número de Celda Libre (1-8) de donde se toma la carta: ");
                    int col = pedirNumero("Escribe el número de Columna (1-8) donde se colocará: ");
                    exito = solitario.moverCeldaLibreATableau(celda, col);
                    mostrarResultado(exito);
                }
                case "F" -> {
                    if (solitario.deshacer()) {
                        System.out.println(">> Último movimiento deshecho.");
                    } else {
                        System.out.println(">> No hay movimientos para deshacer.");
                    }
                }
                case "G" -> {
                    String pista = solitario.obtenerPista();
                    if (pista != null) {
                        System.out.println(">> PISTA: " + pista);
                    } else {
                        System.out.println(">> No hay movimientos disponibles.");
                    }
                }
                default -> System.out.println(">> Operación desconocida.");
            }

            System.out.println(solitario);
            if (!solitario.isGameOver() && solitario.hayMovimientosDisponibles()) {
                menu();
                System.out.print("¿Qué operación quieres hacer?: ");
                op = sc.nextLine().trim().toUpperCase();
            }
        }

        if (solitario.isGameOver()) {
            System.out.println("¡FELICIDADES! Has completado todas las fundaciones. GAME OVER.");
        } else if (!solitario.hayMovimientosDisponibles()) {
            System.out.println("¡AVISO! Ya no es posible realizar más movimientos. GAME OVER.");
        } else {
            System.out.println("Juego finalizado por el usuario.");
        }
    }

    private void menu() {
        System.out.println("Opciones de movimiento:");
        System.out.println("  A) Mover de Columna (Tableau) a Fundación (Base)");
        System.out.println("  B) Mover de Columna (Tableau) a Celda Libre (Eight Off)");
        System.out.println("  C) Mover de Columna (Tableau) a Columna (Tableau)");
        System.out.println("  D) Mover de Celda Libre (Eight Off) a Fundación (Base)");
        System.out.println("  E) Mover de Celda Libre (Eight Off) a Columna (Tableau)");
        System.out.println("  F) Deshacer último movimiento (Undo)");
        System.out.println("  G) Pedir una pista");
        System.out.println("  X) Salir");
    }

    private int pedirNumero(String mensaje) {
        System.out.print(mensaje);
        String linea = sc.nextLine().trim();
        try {
            return Integer.parseInt(linea);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private void mostrarResultado(boolean exito) {
        if (exito) {
            System.out.println(">> Movimiento realizado con éxito.");
        } else {
            System.out.println(">> Movimiento inválido según las reglas de Eight Off.");
        }
    }

    public static void main(String[] args) {
        SolitarioTextUI ui = new SolitarioTextUI();
        ui.playGame();
    }
}
