package com;

import com.tateti.MiniMax;
import com.tateti.Tablero;

import java.util.Scanner;

public class App {
    private Tablero tablero = new Tablero();
    private Scanner scanner = new Scanner(System.in);

    public Integer get_jugada() {
        System.out.println("Ingrese la casilla a jugar");
        Integer movimiento = scanner.nextInt();
        while (!tablero.get_movimientos_legales().contains(movimiento)) {
            System.out.println("Ingresá una casilla válida");
            Integer mov2 = scanner.nextInt();
            movimiento = mov2;
        }
        return movimiento;
    }

    private void jugar_tateti() {
        while (true) {
            Integer movimiento_humano = get_jugada();
            tablero = tablero.mover(movimiento_humano);

            if(tablero.hay_ganador()) {
                System.out.println("Los humanos dominan");
                break;
            } else if(tablero.hay_empate()) {
                System.out.println("Sudando la gota gorda");
                break;
            }

            Integer movimiento_compu = MiniMax.encontrarMejorMovimiento(tablero, 9);
            System.out.println("la computadora jugó " + movimiento_compu);
            tablero = tablero.mover(movimiento_compu);

            System.out.println(tablero);

            if(tablero.hay_ganador()) {
                System.out.println("La rebelión ha comenzado!");
                break;
            } else if(tablero.hay_empate()) {
                System.out.println("Sudando la gota gorda");
                break;
            }
        }
    }

    public static void main(String args[]) {
        App app = new App();
        app.jugar_tateti();
    }
}
