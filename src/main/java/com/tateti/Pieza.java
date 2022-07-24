package com.tateti;

public enum Pieza {
    X, O, V;

    @Override
    public String toString() {
        return switch (this) {
            case X -> "X";
            case O -> "O";
            default -> " ";
        };
    }

    public Pieza rival() {
        return switch (this) {
            case X -> O;
            case O -> X;
            default -> V;
        };
    }
}
