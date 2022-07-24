package com.tateti;

public class MiniMax {
    public static double minimax(Tablero tablero, boolean max, Pieza jugador_original, int profundidad) {
        if(tablero.hay_ganador() || tablero.hay_empate() || profundidad == 0)
            return tablero.evaluar(jugador_original);

        if(max) {
            double mejorEvaluacion = Double.NEGATIVE_INFINITY;
            for(Object movimiento : tablero.get_movimientos_legales()) {
                double resultado = minimax(tablero.mover((Integer) movimiento), false, jugador_original, profundidad - 1);
                mejorEvaluacion = Math.max(mejorEvaluacion, resultado);
            }
            return mejorEvaluacion;
        } else {
            double mejorEvaluacion = Double.POSITIVE_INFINITY;
            for(Object movimiento : tablero.get_movimientos_legales()) {
                double resultado = minimax(tablero.mover((Integer) movimiento), true, jugador_original, profundidad - 1);
                mejorEvaluacion = Math.min(mejorEvaluacion, resultado);
            }
            return mejorEvaluacion;
        }
    }

    public static Integer encontrarMejorMovimiento(Tablero tablero, int profundidad_max) {
        double mejor_valor = Double.NEGATIVE_INFINITY;
        Object mejor_movimiento = null;
        for(Object movimiento : tablero.get_movimientos_legales()) {
            double resultado = minimax(tablero.mover((Integer) movimiento), false, tablero.obtener_turno(), profundidad_max);
            if(resultado > mejor_valor) {
                mejor_valor = resultado;
                mejor_movimiento = movimiento;
            }
        }
        return (Integer) mejor_movimiento;
    }

    /*public static double minimax_plus_apha_beta(Tablero tablero, boolean max, Pieza jugador_original, int profundidad, double alpha, double beta) {
        if(tablero.hay_ganador() || tablero.hay_empate() || profundidad == 0)
            return tablero.evaluar(jugador_original);

        if(max) {
            double max_valor = Double.NEGATIVE_INFINITY;
            for(Object movimiento : tablero.get_movimientos_legales()) {
                double resultado = minimax(tablero.mover((Integer) movimiento), false, jugador_original, profundidad - 1, alpha, beta);
                max_valor = Math.max(max_valor, resultado);
                alpha = (int) Math.max(alpha, resultado);
                if(beta <= alpha)
                    break;
            }
            return max_valor;
        } else {
            double min_valor = Double.POSITIVE_INFINITY;
            for(Object movimiento : tablero.get_movimientos_legales()) {
                double resultado = minimax(tablero.mover((Integer) movimiento), true, jugador_original, profundidad - 1, alpha, beta);
                min_valor = Math.min(min_valor, resultado);
                beta = (int) Math.min(beta, resultado);
                if(beta <= alpha)
                    break;
            }
            return min_valor;
        }
    }*/
}
