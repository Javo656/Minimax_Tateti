package com.tateti;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tablero {
    private static final int CANT_CASILLAS = 9;
    private Pieza[] casillas;
    private Pieza turno_actual;

    public Tablero() {
        this.casillas = new Pieza[CANT_CASILLAS];
        this.turno_actual = Pieza.X;
        Arrays.fill(casillas, Pieza.V);
    }

    public Tablero(Pieza[] casillas, Pieza turno_actual) {
        this.casillas = casillas;
        this.turno_actual = turno_actual;
    }

    public Pieza obtener_turno() {
        return this.turno_actual;
    }

    public Tablero mover(Integer casilla) {
        Pieza[] nuevas_casillas = Arrays.copyOf(this.casillas, this.casillas.length);
        nuevas_casillas[(int) casilla] = this.turno_actual;
        return new Tablero(nuevas_casillas, this.turno_actual.rival());
    }

    public List get_movimientos_legales() {
        var mov_legales = new ArrayList<Integer>();
        for (int i = 0; i < casillas.length; i++) {
            if(casillas[i] == Pieza.V)
                mov_legales.add(i);
        }
        return mov_legales;
    }

    public boolean hay_empate() {
        return !hay_ganador() && get_movimientos_legales().isEmpty();
    }

    public boolean hay_ganador() {
        return  mismo_valor(0, 1, 2) ||
                mismo_valor(3, 4, 5) ||
                mismo_valor(6, 7, 8) ||

                mismo_valor(0, 3, 6) ||
                mismo_valor(1, 4, 7) ||
                mismo_valor(2, 5, 8) ||

                mismo_valor(0, 4, 8) ||
                mismo_valor(2,4, 6);
    }

    public boolean mismo_valor(int c1, int c2, int c3) {
        return casillas[c1] == casillas[c2] &&
               casillas[c2] == casillas[c3] &&
               casillas[c1] != Pieza.V;
    }

    public double evaluar(Pieza jugador) {
        if (this.hay_ganador() && turno_actual == jugador)
            return -1;
        else if (this.hay_ganador() && turno_actual != jugador)
            return 1;
        else
            return 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for(int row = 0; row < 3; row++) {
            for(int col = 0; col < 3; col++) {
                sb.append(casillas[row * 3 + col].toString());
                if(col != 2)
                    sb.append("|");
            }
            sb.append(System.lineSeparator());
            if(row != 2) {
                sb.append("-----");
                sb.append(System.lineSeparator());
            }
        }
        return sb.toString();
    }
}
